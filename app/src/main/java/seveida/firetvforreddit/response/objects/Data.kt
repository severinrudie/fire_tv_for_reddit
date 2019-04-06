package seveida.firetvforreddit.response.objects

import com.squareup.moshi.Json

class Data {

    @Json(name = "modhash")
    var modhash: String
    @Json(name = "dist")
    var dist: Int = 0
    @Json(name = "children")
    var children: List<Child>? = null
    @Json(name = "after")
    var after: String
    @Json(name = "before")
    var before: Any

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param after
     * @param before
     * @param children
     * @param modhash
     * @param dist
     */
    constructor(modhash: String, dist: Int, children: List<Child>, after: String, before: Any) : super() {
        this.modhash = modhash
        this.dist = dist
        this.children = children
        this.after = after
        this.before = before
    }

}
