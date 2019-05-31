package baron.severin.business_logic

import android.annotation.SuppressLint
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult") // This subscription should always be active
internal class Store(
        private val initialState: State,
        private val reducer: Reducer,
        private val actionObservable: ActionObservable,
        private val stateRelay: StateRelay
) {
    internal fun init() {
        actionObservable.get
                // All reduction needs to happen on the same thread
                .observeOn(Schedulers.computation())
                .scan(initialState) { prevState, action ->
                    reducer(prevState, action)
                }.subscribe { stateRelay.get.accept(it) }
    }
}
