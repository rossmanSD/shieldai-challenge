package ai.shield.app.shieldaichallenge.repository

import ai.shield.app.shieldaichallenge.data.Episode
import ai.shield.app.shieldaichallenge.data.Episodes
import android.content.Context
import android.content.SharedPreferences
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class EpisodeRepository(
    private val sharedPrefs: SharedPreferences,
    private val context: Context
) {
    companion object {
        private const val EPISODE_ID_KEY = "episode_id"
    }

    private val mCurrentlySelectedEpisodeId: MutableStateFlow<Long> = MutableStateFlow(-1)

    /**
     * Represents the currently selected episode, or -1 if none is selected.
     * (StateFlow is being used in place of LiveData)
     */
    val currentlySelectedEpisodeId: StateFlow<Long> = mCurrentlySelectedEpisodeId

    /**
     * The entire list of episodes under consideration, initialized on startup.
     */
    val episodesById: Map<Long, Episode>


    init {
        episodesById = loadEpisodes(context).episodes.associateBy { it.id }
        mCurrentlySelectedEpisodeId.value = sharedPrefs.getLong(EPISODE_ID_KEY, -1)
    }

    /**
     * Sets the currently selected episode id, stores it in shared peferences
     * for later retrieval, and updates the [currentlySelectedEpisodeId] StateFlow.
     */
    fun setCurrentlySelectedEpisodeId(id: Long) {
        sharedPrefs.edit().putLong(EPISODE_ID_KEY, id).apply()
        mCurrentlySelectedEpisodeId.value = id
    }

    /*
     * Loads episodes from a json file, could easily also load
     * episodes from a restful endpoint.
     *
     * note: episodes are being loaded from a json file in the assets folder instead of
     * the original txt file in res/raw.
     */
    private fun loadEpisodes(context: Context): Episodes {
        val episodesJson =
            context.assets.open("game_of_thrones_episodes.json")
                .bufferedReader()
                .use { it.readText() }
        return Episodes.fromJson(episodesJson)
    }
}