package baron.severin.business_logic

import arrow.core.Either
import baron.severin.domain_objects.SubredditDetails
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

// This wrapper class allows it to be injected by Dagger
class StateObservable(val get: Observable<State>)
internal class StateRelay(val get: Relay<State>)

object Loading
data class State(
        val selectedSubreddit: Either<Loading, SubredditDetails>,
        val currentScreen: CurrentScreen
)
