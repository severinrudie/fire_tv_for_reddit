package baron.severin.business_logic

import arrow.core.Either
import baron.severin.domain_objects.SubredditDetails
import io.reactivex.Observable
import io.reactivex.subjects.Subject

// This wrapper class allows it to be injected by Dagger
class StateObservable(val get: Observable<State>)
class StateSubject(val get: Subject<State>)

object Loading
data class State(
        val selectedSubreddit: Either<Loading, SubredditDetails>,
        val currentScreen: CurrentScreen
)
