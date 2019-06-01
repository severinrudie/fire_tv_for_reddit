package baron.severin.business_logic.dagger

import android.content.res.Resources
import arrow.core.Either
import baron.severin.business_logic.*
import baron.severin.common.dagger.AppScope
import baron.severin.io.SubredditRepo
import baron.severin.presentation_objects.Colors
import baron.severin.presentation_objects.Loading
import baron.severin.presentation_objects.ToolbarState
import com.jakewharton.rxrelay2.BehaviorRelay
import com.jakewharton.rxrelay2.PublishRelay
import dagger.Module
import dagger.Provides
import io.reactivex.android.schedulers.AndroidSchedulers
import javax.inject.Named

const val INITIAL_STATE = "INITIAL_STATE"

@Module
object BusinessLogicModule {

    @Provides @AppScope
    fun providesEventRelay(): EventRelay = EventRelay(PublishRelay.create())

    @Provides @AppScope
    internal fun providesEventObservable(eventRelay: EventRelay): EventObservable =
            EventObservable(eventRelay.get.hide())

    @Provides @AppScope
    internal fun providesActionRelay(): ActionRelay = ActionRelay(PublishRelay.create())

    @Provides @AppScope
    internal fun providesActionObservable(relay: ActionRelay): ActionObservable =
            ActionObservable(relay.get.hide())

    @Provides @AppScope
    internal fun providesStateRelay(): StateRelay = StateRelay(BehaviorRelay.create())

    @Provides @AppScope
    internal fun providesStateObservable(relay: StateRelay): StateObservable =
            StateObservable(
                    relay.get.hide()
                            .observeOn(AndroidSchedulers.mainThread())
            )

    @Provides @AppScope
    internal fun providesDispatcher(
            eventObservable: EventObservable,
            actionRelay: ActionRelay,
            subredditRepo: SubredditRepo
    ): Dispatcher =
            Dispatcher(
                    eventObservable,
                    actionRelay,
                    subredditRepo
            )

    @Provides @AppScope
    internal fun providesReducer(resources: Resources): Reducer = Reducer(resources)

    @Provides @AppScope
    internal fun providesStore(
            reducer: Reducer,
            actionObservable: ActionObservable,
            stateRelay: StateRelay,
            @Named(INITIAL_STATE) initialState: State
    ): Store =
            Store(
                    initialState,
                    reducer,
                    actionObservable,
                    stateRelay
            )

    @Provides @Named(INITIAL_STATE)
    fun providesInitialColors(): Colors =
            Colors( // TODO pick real colors (these are to make it obvious where existing values are being used)
                    primary = 0xffff8800, // TODO move to colors resource
                    accent = 0xffcc0000,
                    text = 0xff222222,
                    unreadThread = 0x33999999
            )

    @Provides @Named(INITIAL_STATE)
    internal fun providesInitialState(resources: Resources, @Named(INITIAL_STATE) colors: Colors): State =
            State(
                colors = colors,
                currentScreen = CurrentScreen.SUBREDDIT,
                toolbarState = ToolbarState("r/", resources.getString(R.string.search_for_subreddit)),
                threadList = Either.left(Loading)
            )
}
