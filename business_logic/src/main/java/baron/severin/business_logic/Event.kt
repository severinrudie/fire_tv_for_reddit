package baron.severin.business_logic

import com.jakewharton.rxrelay2.Relay
import io.reactivex.Observable

// This wrapper class allows it to be injected by Dagger
internal class EventObservable(val get: Observable<Event>)
class EventRelay(val get: Relay<Event>)

// Sent by the UI
sealed class Event {
    data class SubredditSelected(val subredditName: String) : Event() // TODO make this CharSequence
}
