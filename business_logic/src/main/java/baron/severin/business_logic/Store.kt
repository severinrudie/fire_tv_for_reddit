package baron.severin.business_logic

import android.annotation.SuppressLint

@SuppressLint("CheckResult") // This subscription should always be active
class Store(initialState: State, actionObservable: ActionObservable, stateSubject: StateSubject) {
    init {
        actionObservable.get.scan(initialState) { prevState, action ->
            reducer(prevState, action)
        }.subscribe { stateSubject.get.onNext(initialState) }
    }
}
