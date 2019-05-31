package baron.severin.business_logic

import android.annotation.SuppressLint

@SuppressLint("CheckResult") // This subscription should always be active
class Store(
        initialState: State,
        reducer: Reducer,
        actionObservable: ActionObservable,
        stateRelay: StateRelay
) {
    init {
        actionObservable.get.scan(initialState) { prevState, action ->
            reducer(prevState, action)
        }.subscribe { stateRelay.get.accept(initialState) }
    }
}
