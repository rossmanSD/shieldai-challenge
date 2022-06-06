package ai.shield.app.shieldaichallenge.data

import com.google.gson.GsonBuilder
import java.net.URL

data class Episode(
    var id: Long = 0,
    val url: URL,
    val name: String,
    val season: Int,
    val number: Int,
    val airdate: String,
    val airtime: String,
    /** runtime in minutes */
    val runtime: Int,
    val image: Image,
    val summary: String,
    val _links: Links
) {
    companion object {
        fun fromJson(json: String) = GsonBuilder().create().fromJson(json, Episode::class.java)
    }
}