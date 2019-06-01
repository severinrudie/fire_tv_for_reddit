package baron.severin.business_logic

import android.content.res.Resources
import arrow.core.Either
import baron.severin.domain_objects.SubredditDetails
import baron.severin.presentation_objects.Loading
import baron.severin.presentation_objects.ThreadItemState
import baron.severin.presentation_objects.ToolbarState

internal class Reducer(private val resources: Resources) {

    operator fun invoke(prevState: State, action: Action): State {
        return when (action) {
            is Action.LoadingSubreddit -> loadingSubreddit(prevState)
            is Action.SubredditLoaded -> subredditLoaded(prevState, action)
        }
    }
    
    private fun loadingSubreddit(prevState: State): State =
            prevState.copy(
                    toolbarState = ToolbarState("r/", resources.getString(R.string.search_for_subreddit)),
                    threadList = Either.left(Loading)
            )

    private fun subredditLoaded(prevState: State, action: Action.SubredditLoaded): State =
            prevState.copy(
                    toolbarState = prevState.toolbarState.copy(title = getToolbarTitle(action.subreddit)),
                    threadList = Either.right(action.subreddit.threadMetadataList.map { threadMetadata ->
                        ThreadItemState(
                                imageUrl = threadMetadata.previewImageUrl,
                                title = threadMetadata.title,
                                author = "u/${threadMetadata.op.username}",
                                voteCount = "${threadMetadata.voteCount.up - threadMetadata.voteCount.down}",
                                comments = "${threadMetadata.commentCount} comments",
                                viewed = false  // TODO this isnt correct. persist viewed info and use it here
                        )
                    })
            )

    private fun getToolbarTitle(subreddit: SubredditDetails): String {
        return "r/${subreddit.subredditMetadata.displayName}"
    }
}
