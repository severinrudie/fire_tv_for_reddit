package seveida.firetvforreddit.response.objects

import com.squareup.moshi.Json

class Child {

    @Json(name = "kind")
    var kind: String
    @Json(name = "data")
    var data: Data_

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param data
     * @param kind
     */
    constructor(kind: String, data: Data_) : super() {
        this.kind = kind
        this.data = data
    }

}
