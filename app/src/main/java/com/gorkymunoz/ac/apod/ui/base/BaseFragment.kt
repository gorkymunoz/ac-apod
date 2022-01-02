package com.gorkymunoz.ac.apod.ui.base

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding


/**
 * Created by Gorky Muñoz on 2/1/2022.
 */
abstract class BaseFragment<ViewBindingType : ViewBinding> : Fragment() {

    private var _binding: ViewBindingType? = null
    protected val binding get() = requireNotNull(_binding)

    abstract fun setUpViewBinding(inflater: LayoutInflater, container: ViewGroup?): ViewBindingType

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = setUpViewBinding(inflater, container)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}