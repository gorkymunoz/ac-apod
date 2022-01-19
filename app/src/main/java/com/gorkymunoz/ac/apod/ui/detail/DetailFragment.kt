package com.gorkymunoz.ac.apod.ui.detail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.gorkymunoz.ac.apod.APODApp
import com.gorkymunoz.ac.apod.data.database.source.APODRoomDataSource
import com.gorkymunoz.ac.apod.data.repository.APODRepository
import com.gorkymunoz.ac.apod.data.server.APODRetrofitClient
import com.gorkymunoz.ac.apod.data.server.source.NasaAPODDataSource
import com.gorkymunoz.ac.apod.databinding.FragmentDetailBinding
import com.gorkymunoz.ac.apod.framework.dispatcherprovider.StandardDispatchers
import com.gorkymunoz.ac.apod.ui.base.BaseFragment
import com.gorkymunoz.ac.apod.usecases.GetAPODByDate
import com.gorkymunoz.ac.apod.usecases.ToggleFavoriteAPOD
import com.gorkymunoz.ac.apod.utils.getVMFactory
import com.gorkymunoz.ac.apod.utils.loadImage

/**
 * A simple [Fragment] subclass.
 * Use the [DetailFragment.newInstance] factory method to
 * create an instance of this fragment.
 */
class DetailFragment : BaseFragment<FragmentDetailBinding>() {

    private val viewModel: DetailViewModel by viewModels {
        getVMFactory {
            DetailViewModel(
                GetAPODByDate(
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
                ),
                ToggleFavoriteAPOD(
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

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        arguments?.let {
            val date = DetailFragmentArgs.fromBundle(it).date
            viewModel.getAPODInformation(date)
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.apod.observe(viewLifecycleOwner) {
            binding.tvDetailName.text = it.title
            binding.ivApod.loadImage(it.url)
            binding.tvDetailExplanation.text = it.explanation
            binding.btnDetailHdImage.isEnabled = !it.hdUrl.isNullOrEmpty()
            binding.toggleDetail.addOnButtonCheckedListener { _, checkedId, _ ->
                val url: String = when (checkedId) {
                    binding.btnDetailHdImage.id -> it.hdUrl!!
                    binding.btnDetailNormalImage.id -> it.url
                    else -> ""
                }
                binding.ivApod.loadImage(url)
            }
            binding.cbFavorite.isChecked = it.isFavorite
            binding.cbFavorite.setOnCheckedChangeListener { _, _ -> viewModel.toggleFavoriteAPOD(it) }
        }
    }

    override fun setUpViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentDetailBinding = FragmentDetailBinding.inflate(inflater, container, false)

    companion object {
        /**
         * Use this factory method to create a new instance of
         * this fragment using the provided parameters.
         *
         * @return A new instance of fragment DetailFragment.
         */
        @JvmStatic
        fun newInstance() =
            DetailFragment()
    }
}