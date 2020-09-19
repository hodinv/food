package com.hodinv.products.screens.types

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hodinv.products.R
import com.hodinv.products.databinding.FragmentTypesBinding
import com.hodinv.products.mvvm.BaseMvvmFragment
import com.hodinv.products.mvvm.viewModelLazyInstance
import com.hodinv.products.screens.types.adapter.ItemsAdapter

class TypesFragment : BaseMvvmFragment<TypesViewModel>() {
    override val viewModel: TypesViewModel by viewModelLazyInstance()


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentTypesBinding>(
            inflater,
            R.layout.fragment_types,
            container,
            false
        ).also { binding ->
            binding.viewModel = viewModel
            binding.adapter = ItemsAdapter(viewModel)
        }
        return binding.root
    }

}