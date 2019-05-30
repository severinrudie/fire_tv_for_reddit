package baron.severin.business_logic

import io.reactivex.Observable
import io.reactivex.subjects.Subject

// This wrapper class allows it to be injected by Dagger
class StateObservable(val get: Observable<State>)
class StateSubject(val get: Subject<State>)

data class State(val temp: String)