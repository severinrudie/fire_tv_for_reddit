package baron.severin.business_logic

import baron.severin.domain_objects.SubredditDetails
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

// This wrapper class allows it to be injected by Dagger
internal class ActionObservable(val get: Observable<Action>)
internal class ActionRelay(val get: Relay<Action>)

internal sealed class Action {
    object LoadingSubreddit : Action()
    data class SubredditLoaded(val subreddit: SubredditDetails) : Action()
}
