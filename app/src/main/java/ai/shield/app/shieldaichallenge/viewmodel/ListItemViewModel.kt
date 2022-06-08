package ai.shield.app.shieldaichallenge.viewmodel

import ai.shield.app.shieldaichallenge.repository.EpisodeRepository
import androidx.lifecycle.ViewModel
import kotlinx.coroutines.flow.MutableStateFlow

class ListItemViewModel(private val repo: EpisodeRepository) : ViewModel() {
    /**
     * Represents the currently selected episode, or -1 if none is selected.
     * (MutableStateFlow is being used in place of MutableLiveData)
     */
    val currentlySelectedEpisodeId = repo.currentlySelectedEpisodeId

    /**
     * Sets the currently selected episode id, stores it in shared peferences
     * for later retrieval, and updates the [currentlySelectedEpisodeId] StateFlow.
     */
    fun setCurrentlySelectedEpisodeId(id: Long) = repo.setCurrentlySelectedEpisodeId(id)

    /**
     * The entire list of episodes under consideration, initialized on startup.
     */
    val episodesById = repo.episodesById
}