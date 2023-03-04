package com.badlog1n.giphyvk

import android.graphics.BitmapFactory
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badlog1n.giphyvk.databinding.ImgItemBinding
import com.bumptech.glide.Glide
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class ContentPhotoAdapter : RecyclerView.Adapter<ContentPhotoAdapter.PhotoHolder>() {
    var recordsList = ArrayList<GifData>()
    class PhotoHolder(item: View) : RecyclerView.ViewHolder(item) {
        private val binding = ImgItemBinding.bind(item)

        fun bind(photoRecord: GifData) {
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    Glide.with(binding.root)
                        .load("https://lh6.ggpht.com/9SZhHdv4URtBzRmXpnWxZcYhkgTQurFuuQ8OR7WZ3R7fyTmha77dYkVvcuqMu3DLvMQ=w300")
                        .into(binding.image)
                }
                finally {
                    binding.imgProgress.visibility = View.GONE
                }

                //if load then hide progress
                /*try {
                    val imageRef = Firebase.storage.reference
                    val maxDownloadSize = 5L * 1024 * 1024 * 1024
                    val bytes =
                        imageRef.child("1/${photoRecord.uri}").getBytes(maxDownloadSize).await()
                    val bmp = BitmapFactory.decodeByteArray(bytes, 0, bytes.size)
                    binding.image.setImageBitmap(bmp)
                    binding.imgProgress.visibility = View.GONE
                } catch (_: Exception) {
                }*/
            }
        }

    }

    fun addPhotoRecord(feedRecord: GifData) {
        recordsList.add(feedRecord)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.img_item, parent, false)

        return PhotoHolder(view)
    }

    override fun onBindViewHolder(holder: PhotoHolder, position: Int) {
        holder.bind(recordsList[position])
    }

    override fun getItemCount(): Int {
        return recordsList.size
    }

    fun clearRecords() {
        recordsList.removeAll(recordsList.toSet())
        notifyItemRangeRemoved(0, recordsList.size)
    }

}

