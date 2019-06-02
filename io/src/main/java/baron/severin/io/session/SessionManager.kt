package baron.severin.io.session

import android.app.Application
import android.content.SharedPreferences
import android.util.Log
import baron.severin.common.dagger.DiConstants
import baron.severin.io.IoConstants
import baron.severin.io.UserlessLoginApi
import baron.severin.io.requestUserlessCredentials
import baron.severin.response_objects.raw.response.login.Token
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import org.threeten.bp.Clock
import org.threeten.bp.Instant
import org.threeten.bp.LocalDateTime
import org.threeten.bp.ZoneOffset
import java.util.*
import javax.inject.Inject
import javax.inject.Named

private const val NO_EXPIRATION_STORED = -1L

// TODO replace this with automatic session management using interceptors
class SessionManager @Inject constructor(
        private val sharedPrefs: SharedPreferences,
        userlessLoginApi: UserlessLoginApi,
        @Named(DiConstants.DEVICE_UUID) deviceId: UUID,
        app: Application
) {

    private val getCurrentToken: Observable<Token> = Observable.fromCallable {
        val token = sharedPrefs.getString(IoConstants.USERLESS_TOKEN, null)
        val expirationRaw = sharedPrefs.getLong(IoConstants.USERLESS_TOKEN_EXPIRATION, NO_EXPIRATION_STORED)

        token to expirationRaw
    }
            .filter { (token, expiration) -> token != null && expiration != NO_EXPIRATION_STORED }
            .map { (token, expiration) -> token!! to expiration }
            .filter { (_, expiration) ->
                val now = LocalDateTime.now()
                val exp = Instant.ofEpochMilli(expiration)
                        .atOffset(ZoneOffset.UTC)
                        .toLocalDateTime()

                now.isBefore(exp)
            }
            .map { (token, _) -> Token(token) }
            .doOnNext { Log.d("SevData: ", "Userless Token: token retrieved locally") }

    private val requestToken: Observable<Token> = userlessLoginApi.requestUserlessCredentials(deviceId, app)
            .subscribeOn(Schedulers.io())
            .filter { it.isSuccessful } // TODO handle failure case
            .toObservable()
            .flatMap { response ->
                val body = response.body()
                return@flatMap when (body) {
                    null -> Observable.empty()
                    else -> Observable.just(body)
                }
            }.doOnNext { auth ->
                val now = LocalDateTime.now(Clock.systemUTC())
                val expirationAt = now.plusSeconds(auth.expiresInSeconds)
                        .toInstant(ZoneOffset.UTC)
                        .toEpochMilli()

                sharedPrefs.edit()
                        .putString(IoConstants.USERLESS_TOKEN, auth.token)
                        .putLong(IoConstants.USERLESS_TOKEN_EXPIRATION, expirationAt)
                        .apply()
            } .doOnNext { Log.d("SevData: ", "Userless Token: token retrieved from server: $it") }
            .map { Token(it.token) }

    val loggedIn: Observable<Token> = getCurrentToken.concatWith(requestToken)
            .take(1)
}
