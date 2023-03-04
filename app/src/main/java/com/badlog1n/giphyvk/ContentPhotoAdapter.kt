package com.badlog1n.giphyvk

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.badlog1n.giphyvk.databinding.ImgItemBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
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
                    binding.imgProgress.visibility = View.VISIBLE

                    Glide.with(binding.root)
                        .load(photoRecord.url)
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.imgProgress.visibility = View.GONE
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                dataSource: com.bumptech.glide.load.DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.imgProgress.visibility = View.GONE
                                return false
                            }

                        })
                        .into(binding.image)
                } catch (e: Exception) {
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
        notifyItemChanged(recordsList.size - 1)
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

