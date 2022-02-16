package com.gorkymunoz.ac.apod.ui.home

import android.content.Intent
import android.graphics.Bitmap
import android.net.Uri
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.MediaController
import android.widget.Toast
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Target
import androidx.palette.graphics.get
import com.gorkymunoz.ac.apod.databinding.HomeFragmentBinding
import com.gorkymunoz.ac.apod.domain.APOD
import com.gorkymunoz.ac.apod.domain.MediaType
import com.gorkymunoz.ac.apod.ui.base.BaseFragment
import com.gorkymunoz.ac.apod.utils.loadImageAsBitmap
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels()

    override fun setUpViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = HomeFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apod.observe(viewLifecycleOwner) {
            bindAPOD(it)
        }

    }

    private fun bindAPOD(apod: APOD) {
        when (apod.mediaType) {
            MediaType.IMAGE -> loadImage(apod.url)
            MediaType.VIDEO -> loadVideo(apod.url)
        }
        binding.vvHomeApod.isVisible = apod.mediaType == MediaType.VIDEO
        binding.ivHomeApod.isVisible = apod.mediaType == MediaType.IMAGE
        binding.tvApodName.text = apod.title
        binding.laHomeLoading.isVisible = apod.url.isEmpty()
        binding.btnHomeLearnMore.setOnClickListener {
            findNavController().navigate(
                HomeFragmentDirections.actionHomeFragmentToDetailFragment2(
                    apod.date
                )
            )
        }
    }

    private fun loadVideo(url: String) {
        val uri = Uri.parse(url)
        val mediaController = MediaController(context)
        mediaController.setAnchorView(binding.vvHomeApod)
        binding.vvHomeApod.setMediaController(mediaController)
        binding.vvHomeApod.setOnErrorListener { _, i, i2 ->
            Log.d(HomeFragment::class.java.name, "What: $i extra: $i2")
            // TODO: implement alert dialog
//            val intent = Intent(Intent.ACTION_VIEW, uri)
//            startActivity(intent)
            true
        }
        binding.vvHomeApod.setVideoURI(uri)
    }

    private fun loadImage(url: String) {
        binding.ivHomeApod.loadImageAsBitmap(url) {
            it?.let {
                apodImageReady(it)
            }
        }
    }

    private fun apodImageReady(resource: Bitmap) {
        val palette = Palette.from(resource).generate()
        Log.d(
            HomeFragment::class.java.canonicalName,
            "targets size: ${palette.targets.size}"
        )
        val dominantSwatch =
            palette[Target.VIBRANT] ?: palette[Target.MUTED]
        dominantSwatch?.let {
            binding.cvHomeApod.setCardBackgroundColor(it.rgb)
            binding.tvApodName.setTextColor(it.titleTextColor)
            binding.btnHomeLearnMore.setTextColor(it.bodyTextColor)
        }
    }

}