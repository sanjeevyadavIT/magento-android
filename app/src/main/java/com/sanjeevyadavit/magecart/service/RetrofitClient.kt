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
//        private const val BASE_URL = "http://34.131.79.89/"
        private const val BASE_URL = "https://magento-demo.mageplaza.com/"
        private const val API_BASE_URL = "${BASE_URL}rest/default/"

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
            .baseUrl(API_BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }
}