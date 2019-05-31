package baron.severin.business_logic

import android.annotation.SuppressLint
import io.reactivex.schedulers.Schedulers

@SuppressLint("CheckResult") // This subscription should always be active
class Store(
        initialState: State,
        reducer: Reducer,
        actionObservable: ActionObservable,
        stateRelay: StateRelay
) {
    init {
        actionObservable.get
                // All reduction needs to happen on the same thread
                .observeOn(Schedulers.computation())
                .scan(initialState) { prevState, action ->
                    reducer(prevState, action)
                }.subscribe { stateRelay.get.accept(it) }
    }
}
