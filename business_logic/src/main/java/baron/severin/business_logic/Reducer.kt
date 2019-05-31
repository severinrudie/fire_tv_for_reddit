package baron.severin.business_logic

import arrow.core.Either
import baron.severin.domain_objects.SubredditDetails

internal class Reducer {

    operator fun invoke(prevState: State, action: Action): State {
        return when (action) {
            is Action.LoadingSubreddit -> loadingSubreddit(prevState)
            is Action.SubredditLoaded -> subredditLoaded(prevState, action)
        }
    }
    
    private fun loadingSubreddit(prevState: State): State =
            prevState.copy(
                    toolbarState = ToolbarState("r/"),
                    threadList = Either.left(Loading)
            )

    private fun subredditLoaded(prevState: State, action: Action.SubredditLoaded): State =
            prevState.copy(
                    toolbarState = prevState.toolbarState.copy(title = getToolbarTitle(action.subreddit)),
                    threadList = Either.right(action.subreddit.threadMetadataList)
            )

    private fun getToolbarTitle(subreddit: SubredditDetails): String {
        return "r/${subreddit.subredditMetadata.displayName}"
    }
}
