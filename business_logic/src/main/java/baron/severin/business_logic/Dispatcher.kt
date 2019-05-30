package baron.severin.business_logic

import android.annotation.SuppressLint

@SuppressLint("CheckResult") // TODO remove
class Dispatcher(eventObservable: EventObservable, actionSubject: ActionSubject) {
    // TODO use relays, not observables
    init {
        eventObservable.get.subscribe { actionSubject.get.onNext(TempAction()) }
    }
}
