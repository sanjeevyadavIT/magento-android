package com.sanjeevyadavit.magecart.service

import com.sanjeevyadavit.magecart.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class RetrofitClient {

    companion object {
        private var INSTANCE: Retrofit? = null
//        private const val BASE_URL = "http://34.131.79.89/rest/default/"
        private const val BASE_URL = "https://magento-demo.mageplaza.com/"
        private const val MEGAPLAZA_BASE_URL = "${BASE_URL}rest/default/"
        // TODO: Get this from Store Config api
        const val MEDIA_URL = "${BASE_URL}/media/catalog/product"

        fun getInstance(): Retrofit {
            var instance = INSTANCE
            if (instance == null) {

                createRetrofitInstance(getClient()).also {
                    instance = it
                    INSTANCE = it
                }
            }
            return instance!!
        }

        private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
            level = HttpLoggingInterceptor.Level.BODY
        }

        private fun getClient() = OkHttpClient.Builder()
            .addInterceptor(getLoggingInterceptor())
            .addInterceptor { chain ->
                val newRequest: Request = chain.request().newBuilder()
                    .addHeader("Authorization", "Bearer ${BuildConfig.MAGENTO_ACCESS_TOKEN}")
                    .build()
                chain.proceed(newRequest)
            }
            .build()

        private fun createRetrofitInstance(client: OkHttpClient) = Retrofit.Builder()
            .client(client)
            .baseUrl(MEGAPLAZA_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}