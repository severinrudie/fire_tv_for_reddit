package baron.severin.business_logic

import io.reactivex.Observable
import io.reactivex.subjects.Subject

// This wrapper class allows it to be injected by Dagger
class ActionObservable(val get: Observable<Action>)
class ActionSubject(val get: Subject<Action>)

sealed class Action
class TempAction : Action()
