package baron.severin.business_logic

import arrow.core.Either
import baron.severin.domain_objects.ThreadMetadata
import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

// This wrapper class allows it to be injected by Dagger
class StateObservable(val get: Observable<State>)
internal class StateRelay(val get: Relay<State>)

data class State(
        val currentScreen: CurrentScreen,
        val toolbarState: ToolbarState,
        val threadList: Either<Loading, List<ThreadMetadata>>
)

data class ToolbarState(
        val title: String
)
object Loading
