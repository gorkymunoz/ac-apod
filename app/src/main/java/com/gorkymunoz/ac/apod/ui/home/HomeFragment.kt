package com.gorkymunoz.ac.apod.ui.home

import android.graphics.Bitmap
import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import androidx.palette.graphics.Palette
import androidx.palette.graphics.Target
import androidx.palette.graphics.get
import com.gorkymunoz.ac.apod.APODApp
import com.gorkymunoz.ac.apod.data.database.source.APODRoomDataSource
import com.gorkymunoz.ac.apod.data.repository.APODRepository
import com.gorkymunoz.ac.apod.data.server.APODRetrofitClient
import com.gorkymunoz.ac.apod.data.server.source.NasaAPODDataSource
import com.gorkymunoz.ac.apod.databinding.HomeFragmentBinding
import com.gorkymunoz.ac.apod.domain.APOD
import com.gorkymunoz.ac.apod.framework.dispatcherprovider.StandardDispatchers
import com.gorkymunoz.ac.apod.ui.base.BaseFragment
import com.gorkymunoz.ac.apod.usecases.GetAPOD
import com.gorkymunoz.ac.apod.utils.getVMFactory
import com.gorkymunoz.ac.apod.utils.loadImageAsBitmap

class HomeFragment : BaseFragment<HomeFragmentBinding>() {

    companion object {
        fun newInstance() = HomeFragment()
    }

    private val viewModel: HomeViewModel by viewModels {
        getVMFactory {
            HomeViewModel(
                GetAPOD(
                    APODRepository(
                        NasaAPODDataSource(
                            APODRetrofitClient.createService(),
                            StandardDispatchers(),
                            "DEMO_KEY"
                        ),
                        APODRoomDataSource(
                            (getHomeActivity().applicationContext as APODApp).db,
                            StandardDispatchers()
                        )
                    )
                )
            )
        }
    }

    override fun setUpViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ) = HomeFragmentBinding.inflate(inflater, container, false)

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apod.observe(this) {
            bindAPOD(it)
        }
        //loadImage("https://apod.nasa.gov/apod/image/2201/LeonardTail_Hattenbach_960.jpg")

    }

    private fun bindAPOD(apod: APOD) {
        loadImage(apod.url)
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