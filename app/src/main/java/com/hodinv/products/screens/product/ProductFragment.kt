package com.hodinv.products.screens.product

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.hodinv.products.R
import com.hodinv.products.databinding.FragmentProductBinding
import com.hodinv.products.mvvm.BaseMvvmFragment
import com.hodinv.products.mvvm.viewModelLazyFactory

class ProductFragment : BaseMvvmFragment<ProductViewModel>() {
    override val viewModel: ProductViewModel by viewModelLazyFactory {
        ProductViewModel.Param(
            arguments?.getString(ARG_ID, "") ?: "",
            arguments?.getString(ARG_CATEGORY, "") ?: ""
        )
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val binding = DataBindingUtil.inflate<FragmentProductBinding>(
            inflater,
            R.layout.fragment_product,
            container,
            false
        ).also { binding ->
            binding.viewModel = viewModel

        }
        return binding.root
    }

    companion object {
        private val ARG_ID = "id"
        private val ARG_CATEGORY = "categoryId"
        fun getInstance(id: String, categoryId: String) =
            ProductFragment().apply {
                arguments = Bundle().apply {
                    putString(ARG_ID, id)
                    putString(ARG_CATEGORY, categoryId)
                }
            }

    }
}