package baron.severin.business_logic

import io.reactivex.Observable
import io.reactivex.subjects.Subject

// This wrapper class allows it to be injected by Dagger
class EventObservable(val get: Observable<Event>)
class EventSubject(val get: Subject<Event>)

// Sent by the UI
sealed class Event
class TempEvent : Event()
