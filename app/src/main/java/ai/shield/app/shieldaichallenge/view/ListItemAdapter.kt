package ai.shield.app.shieldaichallenge.view

import ai.shield.app.shieldaichallenge.data.Episode
import ai.shield.app.shieldaichallenge.databinding.ListItemBinding
import android.annotation.SuppressLint
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coil.load
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import logcat.logcat

class ListItemAdapter(
    private val episodes: List<Episode>,
    val onItemClick: (Episode) -> Unit
) : RecyclerView.Adapter<ListItemAdapter.ListItemViewHolder>() {

    // internal map of episode position in recyclerview
    private val episodeIdToPos = mutableMapOf<Long, Int>()

    // internal storage for the currently selected position
    private var selectedPosition: Int? = null

    var desiredEpisodeIdOnStartup: Long? = null

    // override so we can initiate the viewbinding rather than using findViewById()...
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ListItemViewHolder {
        val itemBinding = ListItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ListItemViewHolder(itemBinding)
    }

    override fun onBindViewHolder(holder: ListItemViewHolder, position: Int) {
        val episode: Episode = episodes[position]
        episodeIdToPos[episode.id] = position
        holder.bind(episode)

        processSelectedItem(position, holder)

        // if no item has been clicked yet
        if (selectedPosition == null) {
            if (episodeIdToPos.containsKey(desiredEpisodeIdOnStartup)) {
                val desiredPosition = episodeIdToPos[desiredEpisodeIdOnStartup]
                logcat("recycler") { "episode position is $desiredPosition, and position is $position" }
                if (position == desiredPosition) holder.itemView.callOnClick()
            }
        }
    }

    private fun processSelectedItem(
        position: Int,
        holder: ListItemViewHolder
    ) {
        if (selectedPosition != null) {
            holder.itemView.isSelected = selectedPosition == position
        }
    }

    override fun getItemCount() = episodes.size

    //  holds one row in the list
    inner class ListItemViewHolder(private val itemBinding: ListItemBinding) : RecyclerView.ViewHolder(itemBinding.root) {
        init {
            itemView.setOnClickListener {
                if (absoluteAdapterPosition >= 0) {
                    selectedPosition = absoluteAdapterPosition
                    onItemClick.invoke(episodes[absoluteAdapterPosition])
                    CoroutineScope(Dispatchers.Main).launch {
                        notifyDataSetChanged()
                    }
                }
            }
        }
        @SuppressLint("SetTextI18n")
        fun bind(episode: Episode) {
            itemBinding.labelThumbnail.load(episode.image.medium.toString()) {
                crossfade(true)
                error(ColorDrawable(Color.RED))
            }
            itemBinding.labelName.text = "S${episode.season} Ep${episode.number}: ${episode.name}"
        }
    }
}