package baron.severin.business_logic

import arrow.core.Either

val initalState = State(
        selectedSubreddit = Either.left(Loading),
        currentScreen = CurrentScreen.SUBREDDIT
)
