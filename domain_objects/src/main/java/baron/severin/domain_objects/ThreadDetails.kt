package baron.severin.domain_objects

data class ThreadDetails(val threadMetadata: ThreadMetadata,
                         val content: String, val topLevelComments: List<Comment>)
