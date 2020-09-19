package com.hodinv.products.services.network

import com.hodinv.products.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

class NetworkProviderImpl(private val baseUrl: String) : NetworkProvider {

    private val commonApi: Retrofit

    init {
        val httpClient = OkHttpClient.Builder()

        addLogging(httpClient)

        val client = httpClient.build()
        commonApi = Retrofit.Builder()
            .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .baseUrl(baseUrl)
            .build()

    }


    private fun addLogging(httpClient: OkHttpClient.Builder) {
        val loggingInterceptor = HttpLoggingInterceptor()
        loggingInterceptor.level =
            if (BuildConfig.DEBUG) HttpLoggingInterceptor.Level.BODY else HttpLoggingInterceptor.Level.NONE
        httpClient.addInterceptor(loggingInterceptor)
    }

    override val api: DataApi
        get() = commonApi.create(DataApi::class.java)
}