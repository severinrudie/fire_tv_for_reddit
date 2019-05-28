package seveida.firetvforreddit.subreddit


import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_subreddit.subredditNameTV
import kotlinx.android.synthetic.main.fragment_subreddit.subredditThreadRV
import seveida.firetvforreddit.R
import seveida.firetvforreddit.RedditApplication

class SubredditFragment : Fragment() {

    private val compositeDisposable = CompositeDisposable()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_subreddit, container, false)

    override fun onStart() {
        super.onStart()

        val adapter = SubredditAdapter()

        // TODO decide on DI strategy and replace this
        val subredditRepo = (this.activity!!.application as RedditApplication).subredditRepo
        val subredditDetailsObs = subredditRepo.getSubreddit("aww")
        compositeDisposable += subredditDetailsObs.subscribe { subredditDetails ->
            adapter.setItems(subredditDetails.threadMetadataList)
            subredditNameTV.text = subredditDetails.subredditMetadata.displayName
        }

        with(subredditThreadRV) {
            this.adapter = adapter
            layoutManager = LinearLayoutManager(context)
        }
    }

    override fun onStop() {
        super.onStop()

        compositeDisposable.clear()
    }
}
