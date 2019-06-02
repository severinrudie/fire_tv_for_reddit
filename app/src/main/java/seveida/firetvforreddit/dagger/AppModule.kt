package seveida.firetvforreddit.dagger

import android.app.Application
import android.content.Context
import android.content.SharedPreferences
import baron.severin.common.dagger.AppScope
import baron.severin.common.dagger.DiConstants.APP_ID
import baron.severin.common.dagger.DiConstants.DEVICE_UUID
import dagger.Module
import dagger.Provides
import seveida.firetvforreddit.BuildConfig
import java.util.*
import javax.inject.Named

private const val MAIN_SHARED_PREFS = "MAIN_SHARED_PREFS"

@Module
object AppModule {

    @Provides @Named(APP_ID)
    internal fun providesAppId(): String =
            BuildConfig.APPLICATION_ID

    @Provides
    internal fun providesSharedPrefs(app: Application): SharedPreferences =
            app.getSharedPreferences(MAIN_SHARED_PREFS, Context.MODE_PRIVATE)

    @Provides @Named(DEVICE_UUID)
    internal fun providesDeviceUuid(sharedPrefs: SharedPreferences): UUID {
        // This intentionally does not retain UUIDs between reinstalls / data
        // clears, to respect user privacy
        val maybeUuid = sharedPrefs.getString(DEVICE_UUID, null)
        return when {
            maybeUuid != null -> UUID.fromString(maybeUuid)
            else -> {
                val uuid = UUID.randomUUID()
                sharedPrefs.edit().putString(DEVICE_UUID, uuid.toString()).apply()
                uuid
            }
        }
    }
}
