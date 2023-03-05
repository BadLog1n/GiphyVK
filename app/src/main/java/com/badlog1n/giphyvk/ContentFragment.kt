package com.badlog1n.giphyvk

import android.content.*
import android.graphics.BitmapFactory
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.badlog1n.giphyvk.databinding.FragmentContentBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import java.net.URISyntaxException


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
        //        bundle.putString("url", image)
        //        bundle.putString("title", gifData?.title)
        //        bundle.putString("name", gifData?.name)
        //        bundle.putString("rating", gifData?.rating)
        //        bundle.putString("time", gifData?.time)
        //        bundle.putString("link", gifData?.link)
        val bundle = this.arguments
        if (bundle != null) {
            binding.title.text = bundle.getString("title")
            binding.name.text = bundle.getString("name")
            binding.rating.text = bundle.getString("rating")
            binding.time.text = bundle.getString("time")
            binding.link.text = bundle.getString("link")
/*            val imageRc: RecyclerView = view.findViewById(R.id.imagesRcView)
            imageRc.adapter = rcAdapter
            rcAdapter.recordsList = ArrayList()
            val photoPathSplit = bundle.getString("url")?.split(";")
            photoPathSplit?.forEach { item ->
                rcAdapter.addPhotoRecord(
                    PhotoRecord(item)
                )
            }
            rcAdapter.notifyItemChanged(rcAdapter.itemCount)
            val linearLayoutManager =
                LinearLayoutManager(this.context, LinearLayoutManager.HORIZONTAL, true)
            linearLayoutManager.stackFromEnd = true
            imageRc.layoutManager = linearLayoutManager*/

        }
        super.onViewCreated(view, savedInstanceState)


    }


    override fun onItemClicked(image: String) {
        TODO("Not yet implemented")
    }

}