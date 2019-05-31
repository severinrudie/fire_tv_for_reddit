package baron.severin.business_logic

import javax.inject.Inject

class DataFlowInitializer @Inject internal constructor(
    private val dispatcher: Dispatcher,
    private val store: Store
) {
    fun init() {
        dispatcher.init()
        store.init()
    }
}
