package com.hodinv.products.mvvm

import androidx.fragment.app.Fragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.android.x.closestKodein

abstract class BaseMvvmFragment<VM : BaseMvvmViewModel<*>> : Fragment(), KodeinAware {

    override val kodein: Kodein by closestKodein()

    abstract val viewModel: VM

}