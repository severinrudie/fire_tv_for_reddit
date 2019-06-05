package baron.severin.response_objects.raw.response.api.json

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Gildings(
    @Json(name = "gid_1") val gid1: Int = 0,
    @Json(name = "gid_2") val gid2: Int = 0,
    @Json(name = "gid_3") val gid3: Int = 0
)
