package baron.severin.response_objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class SubredditResponse (
    @Json(name = "kind") val kind: String,
    @Json(name = "data") val data: Data
)
