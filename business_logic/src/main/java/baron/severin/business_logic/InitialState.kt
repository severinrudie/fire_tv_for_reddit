package baron.severin.business_logic

import arrow.core.Either

val initalState = State(
        currentScreen = CurrentScreen.SUBREDDIT,
        toolbarState = ToolbarState("r/"),
        threadList = Either.left(Loading)
)
