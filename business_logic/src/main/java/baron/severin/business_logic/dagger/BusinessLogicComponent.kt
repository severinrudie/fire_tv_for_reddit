package baron.severin.business_logic.dagger

import baron.severin.business_logic.ActionRelay
import baron.severin.business_logic.StateObservable
import dagger.Component

@BusinessLogicScope
@Component(modules = [BusinessLogicModule::class])
interface BusinessLogicComponent {
    fun actionRelay(): ActionRelay
    fun stateObservable(): StateObservable
}
