package com.hodinv.products

import android.app.Application
import android.content.Context
import com.hodinv.products.interactors.PrepareDataInteractor
import com.hodinv.products.interactors.PrepareDataInteractorImpl
import com.hodinv.products.interactors.ProductsListInteractor
import com.hodinv.products.interactors.ProductsListInteractorImpl
import com.hodinv.products.screens.product.ProductViewModel
import com.hodinv.products.screens.types.TypesViewModel
import com.hodinv.products.services.network.DataApi
import com.hodinv.products.services.network.NetworkProvider
import com.hodinv.products.services.network.NetworkProviderImpl
import com.hodinv.products.services.repository.ProductsRepository
import com.hodinv.products.services.repository.ProductsRepositoryImpl
import org.kodein.di.Kodein
import org.kodein.di.KodeinAware
import org.kodein.di.generic.*

class ApplicationProducts : Application(), KodeinAware {

    override val kodein by lazy {
        Kodein {
            bind<Context>() with singleton { this@ApplicationProducts }
            bind<NetworkProvider>() with singleton { NetworkProviderImpl(getString(R.string.base_url)) }
            bind<DataApi>() with singleton { instance<NetworkProvider>().api }
            bind<ProductsRepository>() with singleton { ProductsRepositoryImpl() }
            bind<PrepareDataInteractor>() with singleton {
                PrepareDataInteractorImpl(
                    instance(),
                    instance()
                )
            }
            bind<ProductsListInteractor>() with singleton {
                ProductsListInteractorImpl(
                    instance(),
                    instance()
                )
            }
            bind<MainViewModel>() with singleton { MainViewModel() }
            bind<TypesViewModel>() with provider { TypesViewModel(instance(), instance()) }
            bind<ProductViewModel>() with factory { param: ProductViewModel.Param ->
                ProductViewModel(
                    param,
                    instance(),
                    instance()
                )
            }
        }
    }

    override fun onCreate() {
        super.onCreate()
        kodein.run { }
    }
}