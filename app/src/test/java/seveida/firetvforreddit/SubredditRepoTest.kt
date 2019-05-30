package seveida.firetvforreddit

import baron.severin.io.RetrofitProvider
import org.junit.Test
import org.junit.runner.RunWith
import org.robolectric.RobolectricTestRunner
import baron.severin.io.SubredditRepo

@RunWith(RobolectricTestRunner::class)
class SubredditRepoTest {

    @Test
    fun t() {
        val retrofit = RetrofitProvider.create()

        val repo = SubredditRepo(retrofit)

        repo.getSubreddit("aww")
            .subscribe { (_, threadMetadataList) -> threadMetadataList.forEach { (_, _, title) -> println(title) } }

        try {
            Thread.sleep(10000)
        } catch (e: Exception) {

        }

    }

}