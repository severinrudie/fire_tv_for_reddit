package seveida.firetvforreddit.response.objects

import com.squareup.moshi.Json

class Gildings {

    @Json(name = "gid_1")
    var gid1: Int = 0
    @Json(name = "gid_2")
    var gid2: Int = 0
    @Json(name = "gid_3")
    var gid3: Int = 0

    /**
     * No args constructor for use in serialization
     *
     */
    constructor() {}

    /**
     *
     * @param gid1
     * @param gid3
     * @param gid2
     */
    constructor(gid1: Int, gid2: Int, gid3: Int) : super() {
        this.gid1 = gid1
        this.gid2 = gid2
        this.gid3 = gid3
    }

}
