package seveida.firetvforreddit.response.objects

import com.squareup.moshi.Json

class Image {

    @Json(name = "source")
    var source: Source
    @Json(name = "resolutions")
    var resolutions: List<Resolution>? = null
    @Json(name = "variants")
    var variants: Variants
    @Json(name = "id")
    var id: String

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param id
     * @param source
     * @param variants
     * @param resolutions
     */
    constructor(source: Source, resolutions: List<Resolution>, variants: Variants, id: String) : super() {
        this.source = source
        this.resolutions = resolutions
        this.variants = variants
        this.id = id
    }

}
