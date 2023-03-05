package com.badlog1n.giphyvk

import android.graphics.drawable.Drawable
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.badlog1n.giphyvk.databinding.FragmentContentBinding
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch


class ContentFragment : Fragment(), ContentPhotoAdapter.RecyclerViewEvent {
    private lateinit var binding: FragmentContentBinding
    private var rcAdapter = ContentPhotoAdapter(this)
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?
    ): View {
        binding = FragmentContentBinding.inflate(layoutInflater, container, false)
        return binding.root

    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        val bundle = this.arguments
        if (bundle != null) {
            binding.title.text = bundle.getString("title")
            binding.name.text = bundle.getString("name")
            binding.rating.text = bundle.getString("rating")
            binding.time.text = bundle.getString("time")
            binding.link.text = bundle.getString("link")
            CoroutineScope(Dispatchers.Main).launch {
                try {
                    binding.toImgProgress.visibility = View.VISIBLE

                    Glide.with(binding.root)
                        .load(bundle.getString("bigGif"))
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.toImgProgress.visibility = View.GONE
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                dataSource: com.bumptech.glide.load.DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.toImgProgress.visibility = View.GONE
                                return false
                            }

                        })
                        .into(binding.mainImage)
                } catch (e: Exception) {
                    binding.toImgProgress.visibility = View.GONE
                }
                try {
                    binding.downSizedProgress.visibility = View.VISIBLE

                    Glide.with(binding.root)
                        .load(bundle.getString("downSized"))
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.downSizedProgress.visibility = View.GONE
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                dataSource: com.bumptech.glide.load.DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.downSizedProgress.visibility = View.GONE
                                return false
                            }

                        })
                        .into(binding.downSized)
                } catch (e: Exception) {

                    binding.downSizedProgress.visibility = View.GONE
                }
                try {
                    binding.downSizedSmallProgress.visibility = View.VISIBLE

                    Glide.with(binding.root)
                        .load(bundle.getString("downSizedSmall"))
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.downSizedSmallProgress.visibility = View.GONE
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                dataSource: com.bumptech.glide.load.DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.downSizedSmallProgress.visibility = View.GONE
                                return false
                            }

                        })
                        .into(binding.downSizedSmall)
                } catch (e: Exception) {
                    binding.downSizedSmallProgress.visibility = View.GONE
                }
                try {
                    binding.originalMp4Progress.visibility = View.VISIBLE

                    Glide.with(binding.root)
                        .load(bundle.getString("originalMp4"))
                        .listener(object : RequestListener<Drawable> {
                            override fun onLoadFailed(
                                e: GlideException?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.originalMp4Progress.visibility = View.GONE
                                return false
                            }

                            override fun onResourceReady(
                                resource: Drawable?,
                                model: Any?,
                                target: com.bumptech.glide.request.target.Target<Drawable>?,
                                dataSource: com.bumptech.glide.load.DataSource?,
                                isFirstResource: Boolean
                            ): Boolean {
                                binding.originalMp4Progress.visibility = View.GONE
                                return false
                            }

                        })
                        .into(binding.originalMp4)
                } catch (e: Exception) {
                    binding.originalMp4Progress.visibility = View.GONE
                }
            }


        }
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onItemClicked(image: String) {
        TODO("Not yet implemented")
    }

}