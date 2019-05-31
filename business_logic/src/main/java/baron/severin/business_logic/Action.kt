package baron.severin.business_logic

import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

// This wrapper class allows it to be injected by Dagger
class ActionObservable(val get: Observable<Action>)
class ActionRelay(val get: Relay<Action>)

sealed class Action
class TempAction : Action()
