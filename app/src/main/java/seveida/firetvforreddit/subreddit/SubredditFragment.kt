package seveida.firetvforreddit.subreddit


import android.content.Context
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.LinearLayoutManager
import baron.severin.business_logic.*
import dagger.android.support.AndroidSupportInjection
import io.reactivex.disposables.CompositeDisposable
import io.reactivex.rxkotlin.plusAssign
import kotlinx.android.synthetic.main.fragment_subreddit.*
import seveida.firetvforreddit.R
import javax.inject.Inject

class SubredditFragment : Fragment() {

    @Inject lateinit var stateObsWrapper: StateObservable
    @Inject lateinit var eventRelayWrapper: EventRelay
    @Inject lateinit var subredditAdapter: SubredditAdapter

    private val stateObs get() = stateObsWrapper.get
    private val eventRelay get() = eventRelayWrapper.get

    private val compositeDisposable = CompositeDisposable()

    override fun onAttach(context: Context?) {
        AndroidSupportInjection.inject(this)
        super.onAttach(context)
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View? =
        inflater.inflate(R.layout.fragment_subreddit, container, false)

    override fun onStart() {
        super.onStart()

        observeState()

        with(subredditThreadRV) {
            this.adapter = subredditAdapter
            layoutManager = LinearLayoutManager(context)
        }

        // TODO temp
        eventRelay.accept(Event.SubredditSelected("aww"))
    }

    private fun observeState() {
        compositeDisposable += stateObs
                .map { it.selectedSubreddit }
                .subscribe {
                    it.fold(
                            ifLeft = { loading -> setLoading() },
                            ifRight = { subreddit ->
                                subredditAdapter.setItems(subreddit.threadMetadataList)
                                subredditNameTV.text = subreddit.subredditMetadata.displayName
                            }
                    )
                }
    }

    override fun onStop() {
        super.onStop()

        compositeDisposable.clear()
    }

    private fun setLoading() {
        // TODO
    }
}
