package com.hodinv.products

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import com.hodinv.products.screens.Screens
import com.hodinv.products.screens.product.ProductFragment
import com.hodinv.products.screens.types.TypesFragment
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.KodeinContext
import org.kodein.di.android.closestKodein
import org.kodein.di.generic.bind
import org.kodein.di.generic.instance
import org.kodein.di.generic.kcontext
import org.kodein.di.generic.provider

class MainActivity : AppCompatActivity(), KodeinAware {

    override val kodeinContext: KodeinContext<*> = kcontext(this)

    private val _parentKodein by closestKodein()
    override val kodein: Kodein by lazy {
        Kodein {
            extend(_parentKodein)
            bind() from provider { this@MainActivity }
        }
    }

    private val viewModel: MainViewModel by instance()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        if (savedInstanceState == null) {
            startFragment(TypesFragment())
        }
        viewModel.nextScreen.observe(this, Observer { screen ->
            when (screen) {
                is Screens.Product -> {
                    startFragmentWithStacking(ProductFragment.getInstance(screen.id, screen.categoryId))
                }
            }
        })
    }


    /**
     * Replace current content fragment with new one
     * @param newFragment fragment to show
     */
    private fun startFragment(newFragment: androidx.fragment.app.Fragment) {
        for (i in 0 until supportFragmentManager.backStackEntryCount) {
            supportFragmentManager.popBackStack()
        }
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment, TAG_FRAGMENT)
        transaction.commit()
    }


    /**
     * Adds current fragment ot back stack and shows new fragment
     * @param newFragment fragment to show
     */
    private fun startFragmentWithStacking(newFragment: androidx.fragment.app.Fragment) {
        val transaction = supportFragmentManager.beginTransaction()
        transaction.replace(R.id.container, newFragment, TAG_FRAGMENT).addToBackStack(null)
        transaction.commit()
    }


    companion object {
        const val TAG_FRAGMENT = "currentFragment"
    }

}