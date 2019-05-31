package baron.severin.business_logic.dagger

import baron.severin.business_logic.*
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import dagger.Module
import dagger.Provides

@Module
object BusinessLogicModule {

    @Provides @BusinessLogicScope
    fun providesActionRelay(): ActionRelay = ActionRelay(PublishRelay.create())

    @Provides @BusinessLogicScope
    internal fun providesActionObservable(relay: ActionRelay): ActionObservable =
            ActionObservable(relay.get.hide())

    @Provides @BusinessLogicScope
    internal fun providesStateRelay(): StateRelay = StateRelay(BehaviorRelay.create())

    @Provides @BusinessLogicScope
    fun providesStateObservable(relay: StateRelay): StateObservable =
            StateObservable(relay.get.hide())

    @Provides @BusinessLogicScope
    internal fun providesDispatcher(
            eventObservable: EventObservable,
            actionRelay: ActionRelay
    ): Dispatcher =
            Dispatcher(
                    eventObservable,
                    actionRelay
            )

    @Provides @BusinessLogicScope
    internal fun providesReducer(): Reducer = Reducer()

    @Provides @BusinessLogicScope
    internal fun providesStore(
            reducer: Reducer,
            actionObservable: ActionObservable,
            stateRelay: StateRelay
    ): Store =
            Store(
                    initalState,
                    reducer,
                    actionObservable,
                    stateRelay
            )
}
