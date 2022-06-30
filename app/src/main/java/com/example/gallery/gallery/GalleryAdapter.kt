package com.example.gallery.gallery

import android.content.Context
import android.media.MediaMetadataRetriever
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.gallery.Attachments
import com.example.gallery.OnPictureItemClickListener
import com.example.gallery.R
import com.example.gallery.databinding.GalleryImageItemViewBinding
import java.util.concurrent.TimeUnit

class GalleryAdapter(
    private val context: Context,
    private val itemClickListener: OnPictureItemClickListener? = null,
    private var items: List<Attachments> = listOf()
) : RecyclerView.Adapter<GalleryAdapter.GalleryViewHolder>() {

    override fun getItemCount() = items.size

    inner class GalleryViewHolder(val binding: GalleryImageItemViewBinding) :
        RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): GalleryViewHolder {
        val binding =
            GalleryImageItemViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return GalleryViewHolder(binding)
    }

    override fun onBindViewHolder(holder: GalleryViewHolder, position: Int) {
        holder.binding.apply {
            val item = items[position]
            container.setOnClickListener {
                itemClickListener?.onClick(item, position)
            }
            Glide.with(context).load(item.thumbnail)
                .error(R.drawable.ic_custom_error_placeholder).into(imageViewDownloader)
            if (item.type == "VID") {
                playButton.visibility = View.VISIBLE
            } else {
                playButton.visibility = View.GONE
            }
        }
    }
    /*get video duration from url*/
    private fun getVideoDuration(url: String): String {
        val retriever = MediaMetadataRetriever()
        retriever.setDataSource(url, HashMap())
        val time = retriever.extractMetadata(MediaMetadataRetriever.METADATA_KEY_DURATION)
        val timeInMilliSec = time!!.toLong()
        retriever.release()
        return String.format(
            "%d:%02d",
            TimeUnit.MILLISECONDS.toMinutes(timeInMilliSec),
            TimeUnit.MILLISECONDS.toSeconds(timeInMilliSec) -
                    TimeUnit.MINUTES.toSeconds(TimeUnit.MILLISECONDS.toMinutes(timeInMilliSec))
        );
    }
}