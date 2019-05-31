package baron.severin.business_logic

import android.annotation.SuppressLint
import android.util.Log
import javax.inject.Inject

internal class Logger @Inject constructor(
        private val stateObservable: StateObservable,
        private val actionObservable: ActionObservable,
        private val eventObservable: EventObservable
) {

    @SuppressLint("CheckResult") // These subscriptions should always be active
    internal fun init() {
        stateObservable.get.subscribe { Log.d("SevData: State: ", it.toString()) }
        eventObservable.get.subscribe { Log.d("SevData: Event: ", it.toString()) }
        actionObservable.get.subscribe { Log.d("SevData: Action: ", it.toString()) }
    }
}
