package baron.severin.response_objects

import com.squareup.moshi.Json
import com.squareup.moshi.JsonClass

@JsonClass(generateAdapter = true)
data class Data(

        @Json(name = "modhash") val modhash: String,
        @Json(name = "dist") val dist: Int = 0,
        @Json(name = "children") val children: List<Child>,
        @Json(name = "after") val after: String,
        @Json(name = "before") val before: Any?
)
