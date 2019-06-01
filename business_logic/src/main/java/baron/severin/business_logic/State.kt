package baron.severin.business_logic

import arrow.core.Either
import baron.severin.presentation_objects.Loading
import baron.severin.presentation_objects.ThreadItemState
import baron.severin.presentation_objects.ToolbarState
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

// This wrapper class allows it to be injected by Dagger
class StateObservable(val get: Observable<State>)
internal class StateRelay(val get: Relay<State>)

data class State(
        val currentScreen: CurrentScreen,
        val toolbarState: ToolbarState,
        val threadList: Either<Loading, List<ThreadItemState>>
)
