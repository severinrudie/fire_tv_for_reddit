package baron.severin.business_logic

import arrow.core.Either

class Reducer {

    operator fun invoke(prevState: State, action: Action): State {
        return when (action) {
            is Action.LoadingSubreddit -> loadingSubreddit(prevState)
            is Action.SubredditLoaded -> subredditLoaded(prevState, action)
        }
    }
    
    private fun loadingSubreddit(prevState: State): State =
            prevState.copy(
                    selectedSubreddit = Either.left(Loading)
            )

    private fun subredditLoaded(prevState: State, action: Action.SubredditLoaded): State =
            prevState.copy(
                    selectedSubreddit = Either.right(action.subreddit)
            )
}
