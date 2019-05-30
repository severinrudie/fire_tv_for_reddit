package baron.severin.io.dagger

import baron.severin.io.SubredditApi
import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
object IoModule {

    @Provides @IoScope
    internal fun providesOkHttp(): OkHttpClient = OkHttpClient()

    @Provides @IoScope
    internal fun providesRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides @IoScope
    internal fun providesSubredditApi(retrofit: Retrofit): SubredditApi =
            retrofit.create(SubredditApi::class.java)
}
