package seveida.firetvforreddit.subreddit


import android.annotation.SuppressLint
import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import baron.severin.business_logic.StateObservable
import baron.severin.io.SubredditRepo
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_subreddit.subredditNameTV
import kotlinx.android.synthetic.main.fragment_subreddit.subredditThreadRV
import seveida.firetvforreddit.R
import javax.inject.Inject

class SubredditFragment : Fragment() {

    @Inject lateinit var subredditRepo: SubredditRepo
    @Inject lateinit var stateObs: StateObservable

    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_subreddit, container, false)

    @SuppressLint("SetTextI18n") // TODO set text the right way
    override fun onStart() {
        super.onStart()

        val adapter = SubredditAdapter()

        val subredditDetailsObs = subredditRepo.getSubreddit("aww")
        compositeDisposable += subredditDetailsObs.subscribe { subredditDetails ->
            adapter.setItems(subredditDetails.threadMetadataList)
            subredditNameTV.text = "r/${subredditDetails.subredditMetadata.displayName}"
        }
        println(stateObs)

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
