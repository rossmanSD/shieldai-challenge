package ai.shield.app.shieldaichallenge.data

import com.google.gson.GsonBuilder
import java.net.URL

data class Image(
    val medium: URL,
    val original: URL
) {
    companion object {
        fun fromJson(json: String) = GsonBuilder().create().fromJson(json, Image::class.java)
    }
}

data class Links(
    val self: Self
) {
    companion object {
        fun fromJson(json: String) = GsonBuilder().create().fromJson(json, Links::class.java)
    }
}

data class Self(
    val href: String
) {
    companion object {
        fun fromJson(json: String) = GsonBuilder().create().fromJson(json, Self::class.java)
    }
}