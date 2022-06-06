package ai.shield.app.shieldaichallenge.data

import com.google.gson.GsonBuilder

data class Episodes(
    val episodes: List<Episode>
) {
    companion object {
        fun fromJson(json: String) = GsonBuilder().create().fromJson(json, Episodes::class.java)
    }
}