package baron.severin.domain_objects

data class SubredditDetails(val subredditMetadata: SubredditMetadata,
                            val threadMetadataList: List<ThreadMetadata>)
