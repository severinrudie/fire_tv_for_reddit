package baron.severin.business_logic

import javax.inject.Inject

class DataFlowInitializer @Inject internal constructor(
    private val dispatcher: Dispatcher,
    private val store: Store,
    private val logger: Logger
) {
    fun init() {
        dispatcher.init()
        store.init()
        logger.init()
    }
}
