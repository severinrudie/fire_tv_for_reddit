package baron.severin.business_logic

import android.annotation.SuppressLint
import baron.severin.io.SubredditRepo
import io.reactivex.Observable

class Dispatcher(
        eventObservableWrapper: EventObservable,
        actionRelayWrapper: ActionRelay,
        private val subredditRepo: SubredditRepo
) {
    private val eventObservable = eventObservableWrapper.get
    private val actionRelay = actionRelayWrapper.get

    init {
        setup()
    }

    @SuppressLint("CheckResult") // This subscription should always be active
    private fun setup() {
        eventObservable.subscribe { event ->
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
                .map { Action.LoadSubreddit(it) }

        return Observable.just(load)
                .concatWith(loadedObs)
    }
}
