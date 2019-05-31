package baron.severin.business_logic

import android.annotation.SuppressLint
import android.util.Log
import baron.severin.io.SubredditRepo
import io.reactivex.Observable

internal class Dispatcher(
        eventObservableWrapper: EventObservable,
        actionRelayWrapper: ActionRelay,
        private val subredditRepo: SubredditRepo
) {
    private val eventObservable = eventObservableWrapper.get
    private val actionRelay = actionRelayWrapper.get

    @SuppressLint("CheckResult") // This subscription should always be active
    internal fun init() {
        eventObservable
                .doOnNext { Log.d("SevData: Event: ", it.toString()) }
                .subscribe { event ->
            when (event) {
                is Event.SubredditSelected ->
                    getSubreddit(event.subredditName).subscribe { actionRelay.accept(it) }
            }
        }
    }

    // TODO handle failure case
    private fun getSubreddit(name: String): Observable<Action> {
        val load: Action = Action.LoadingSubreddit
        val loadedObs: Observable<Action> = subredditRepo.getSubreddit(name)
                .map { Action.SubredditLoaded(it) }

        return Observable.just(load)
                .concatWith(loadedObs)
    }
}
