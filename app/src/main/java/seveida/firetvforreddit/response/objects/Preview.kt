package seveida.firetvforreddit.response.objects

import com.squareup.moshi.Json


class Preview {

    @Json(name = "images")
    var images: List<Image>? = null
    @Json(name = "enabled")
    var enabled: Boolean = false

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param enabled
     * @param images
     */
    constructor(images: List<Image>, enabled: Boolean) : super() {
        this.images = images
        this.enabled = enabled
    }

}
