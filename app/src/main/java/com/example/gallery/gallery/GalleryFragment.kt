package com.example.gallery

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.recyclerview.widget.GridLayoutManager
import com.example.gallery.databinding.GalleryRecyclerBinding
import com.example.gallery.gallery.GalleryAdapter
import com.example.gallery.gallery.GalleryRecyclerDecoration

interface OnPictureItemClickListener {
    fun onClick(picture: Attachments, position: Int)
}

class GalleryFragment(private val attachments: List<Attachments>) : Fragment() {

    private var _binding: GalleryRecyclerBinding? = null
    private val binding get() = _binding
    private var galleryAdapter: GalleryAdapter? = null

    companion object {
        fun newInstance(attachments: List<Attachments>) = GalleryFragment(attachments)
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = GalleryRecyclerBinding.inflate(inflater, container, false)
        binding?.apply {
            val clickListener = object : OnPictureItemClickListener {
                override fun onClick(picture: Attachments, position: Int) {
                    //TODO: Move to NAV ACTIVITY
                }

            }
            galleryAdapter = GalleryAdapter(
                requireContext(),
                clickListener,
                attachments
            )
            val gridLayoutManager = GridLayoutManager(requireContext(), 3)
            recycler.apply {
                layoutManager = gridLayoutManager
                addItemDecoration(
                    GalleryRecyclerDecoration(
                        spanCount = 3,
                        spacing = 10,
                        includeEdge = true
                    )
                )
                adapter = galleryAdapter
            }

        }
        return binding?.root
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding = null
    }

}