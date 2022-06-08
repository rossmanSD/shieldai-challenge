package ai.shield.app.shieldaichallenge.view

import ai.shield.app.shieldaichallenge.R
import ai.shield.app.shieldaichallenge.databinding.FragmentDetailBinding
import ai.shield.app.shieldaichallenge.viewmodel.ListItemViewModel
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.text.HtmlCompat
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import coil.load
import kotlinx.coroutines.launch
import logcat.LogPriority
import logcat.logcat
import org.koin.androidx.viewmodel.ext.android.sharedViewModel

class DetailFragment : Fragment() {
    private lateinit var binding: FragmentDetailBinding

    private val listItemViewModel: ListItemViewModel by sharedViewModel()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentDetailBinding.inflate(inflater, container, false)
        setupSelectedEpisodeObserver()
        return binding.root
    }

    private fun setupSelectedEpisodeObserver() {
        lifecycleScope.launch {
            listItemViewModel.currentlySelectedEpisodeId.collect { episodeId ->
                if (episodeId >= 0) {
                    val episode = listItemViewModel.episodesById[episodeId]
                    logcat("recycler", LogPriority.VERBOSE) {
                        "DetailFragment observed onClick for Episode: ${episode?.image?.original}"
                    }
                    if (episode != null) {
                        binding.imageView.load(episode.image.original.toString()) {
                            placeholder(R.drawable.loading_in_progress)
                            error(R.drawable.loading_failure)
                        }
                        binding.description.text = HtmlCompat.fromHtml(
                            episode.summary, HtmlCompat.FROM_HTML_MODE_LEGACY
                        )
                    }
                }
            }
        }
    }
}