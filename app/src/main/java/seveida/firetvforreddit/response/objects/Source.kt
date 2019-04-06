package seveida.firetvforreddit.response.objects

import com.squareup.moshi.Json

class Source {

    @Json(name = "url")
    var url: String
    @Json(name = "width")
    var width: Int = 0
    @Json(name = "height")
    var height: Int = 0

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param height
     * @param width
     * @param url
     */
    constructor(url: String, width: Int, height: Int) : super() {
        this.url = url
        this.width = width
        this.height = height
    }

}
