package baron.severin.business_logic

import baron.severin.domain_objects.SubredditDetails
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

// This wrapper class allows it to be injected by Dagger
class ActionObservable(val get: Observable<Action>)
class ActionRelay(val get: Relay<Action>)

sealed class Action {
    object LoadingSubreddit : Action()
    class SubredditLoaded(val subreddit: SubredditDetails) : Action()
}
