package com.sanjeevyadavit.magecart.service

import com.sanjeevyadavit.magecart.BuildConfig
import okhttp3.OkHttpClient
import okhttp3.Request
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory


abstract class RetrofitClient {

    companion object {
        private var INSTANCE: Retrofit? = null
        private const val BASE_URL = "http://34.131.79.89/rest/default/"

        fun getInstance(): Retrofit {
            var instance = INSTANCE
            if(instance == null) {
                // QUESTION: Was this OkHttpClient necessary to send authorization token?
                val client = OkHttpClient.Builder().addInterceptor { chain ->
                    val newRequest: Request = chain.request().newBuilder()
                        .addHeader("Authorization", "Bearer ${BuildConfig.MAGENTO_ACCESS_TOKEN}")
                        .build()
                    chain.proceed(newRequest)
                }.build()


                instance = Retrofit.Builder()
                    .client(client)
                    .baseUrl(BASE_URL)
                    .addConverterFactory(GsonConverterFactory.create())
                    .build()
                INSTANCE = instance
            }
            return instance!!
        }
    }
}