package baron.severin.io

import dagger.Module
import dagger.Provides
import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.moshi.MoshiConverterFactory

@Module
abstract class IoModule {

    @Provides internal fun providesOkHttp(): OkHttpClient = OkHttpClient()

    @Provides internal fun providesRetrofit(httpClient: OkHttpClient): Retrofit = Retrofit.Builder()
            .baseUrl("https://www.reddit.com")
            .client(httpClient)
            .addConverterFactory(MoshiConverterFactory.create())
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .build()

    @Provides internal fun providesSubredditApi(retrofit: Retrofit): SubredditApi =
            retrofit.create(SubredditApi::class.java)
}
