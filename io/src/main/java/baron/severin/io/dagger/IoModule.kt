package baron.severin.io.dagger

import baron.severin.common.dagger.AppScope
import baron.severin.common.dagger.DiConstants.APP_ID
import baron.severin.common.dagger.DiConstants.DEVICE_UUID
import baron.severin.common.IgnoredSecrets
import baron.severin.io.BuildConfig
import baron.severin.io.Interceptors.LoggingInterceptor
import baron.severin.io.SubredditApi
import dagger.Lazy
import dagger.Module
import dagger.Provides
import io.reactivex.Observable
import io.reactivex.schedulers.Schedulers
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory
import okhttp3.Interceptor
import okhttp3.Response
import java.util.*
import javax.inject.Named


@Module
object IoModule {

    @Provides @AppScope
//    internal fun providesOkHttp(): OkHttpClient = OkHttpClient()
    internal fun providesOkHttp(): OkHttpClient = OkHttpClient.Builder()
            .addInterceptor(LoggingInterceptor())
            .build()

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

}
