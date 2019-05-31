package baron.severin.business_logic

import android.annotation.SuppressLint

@SuppressLint("CheckResult") // This subscription should always be active
class Dispatcher(eventObservable: EventObservable, actionRelay: ActionRelay) {
    // TODO use relays, not observables
    init {
        eventObservable.get.subscribe { actionRelay.get.accept(TempAction()) }
    }
}
