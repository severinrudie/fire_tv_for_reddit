package baron.severin.io.dagger

import baron.severin.common.dagger.AppScope
import baron.severin.common.dagger.DiConstants.APP_ID
import baron.severin.common.dagger.DiConstants.DEVICE_UUID
import baron.severin.common.IgnoredSecrets
import baron.severin.io.BuildConfig
import baron.severin.io.SubredditApi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import net.dean.jraw.RedditClient
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import net.dean.jraw.oauth.OAuthHelper
import net.dean.jraw.http.NetworkAdapter
import net.dean.jraw.http.OkHttpNetworkAdapter
import net.dean.jraw.http.UserAgent
import net.dean.jraw.oauth.Credentials
import java.util.*
import javax.inject.Named


@Module
object IoModule {

    @Provides @AppScope
    internal fun providesOkHttp(): OkHttpClient = OkHttpClient()

    @Provides @AppScope
    internal fun providesRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides @AppScope
    internal fun providesSubredditApi(retrofit: Retrofit): SubredditApi =
            retrofit.create(SubredditApi::class.java)

    @Provides @AppScope
    internal fun providesNetworkAdapter(@Named(APP_ID) appId: String): NetworkAdapter =
            OkHttpNetworkAdapter(UserAgent(
                    platform = "Android",
                    appId = appId,
                    version = BuildConfig.VERSION_NAME,
                    redditUsername = "userless mode"
            ))

    // TODO these are userless creds. An additional method will be required for logged in use
    @Provides @AppScope
    internal fun providesCredentials(
            @Named(DEVICE_UUID) deviceId: UUID
    ): Credentials =
            Credentials.userlessApp(IgnoredSecrets.CLIENT_ID, deviceId)

    /**
     * We need to run this on a background thread because client init makes a network request
     */
    @Provides @AppScope
    internal fun providesRedditClient(networkAdapter: NetworkAdapter, credentials: Credentials): RedditObs =
            RedditObs(
                    Observable.fromCallable { OAuthHelper.automatic(networkAdapter, credentials) }
                            .subscribeOn(Schedulers.io())
                            .replay(1)
                            .autoConnect(0)
            )
}
