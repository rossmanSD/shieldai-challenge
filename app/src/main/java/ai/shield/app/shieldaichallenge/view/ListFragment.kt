package ai.shield.app.shieldaichallenge.view

import ai.shield.app.shieldaichallenge.R
import ai.shield.app.shieldaichallenge.databinding.FragmentListBinding
import ai.shield.app.shieldaichallenge.viewmodel.ListItemViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import logcat.logcat
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class ListFragment : Fragment(R.layout.fragment_list) {
    private lateinit var binding: FragmentListBinding
    private val listItemViewModel: ListItemViewModel by sharedViewModel()

    private lateinit var adapter: ListItemAdapter

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        binding = FragmentListBinding.inflate(inflater, container, false)
        adapter = ListItemAdapter(listItemViewModel.episodesById.values.toList()) { episode ->
            listItemViewModel.setCurrentlySelectedEpisodeId(episode.id)
        }

        binding.recyclerView.adapter = adapter
        setupObservers()
        return binding.root
    }

    private fun setupObservers() {
        lifecycleScope.launch {
            listItemViewModel.currentlySelectedEpisodeId.collect { episodeId ->
                if (episodeId >= 0) {
                    adapter.desiredEpisodeIdOnStartup = episodeId
                }
            }
        }
    }
}