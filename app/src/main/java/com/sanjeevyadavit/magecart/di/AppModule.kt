package com.sanjeevyadavit.magecart.di

import com.sanjeevyadavit.magecart.BuildConfig
import com.sanjeevyadavit.magecart.data.remote.ApiInterface
import com.sanjeevyadavit.magecart.data.repository.MageCartRepositoryImpl
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiInterface(retrofit: Retrofit): ApiInterface =
        retrofit.create(ApiInterface::class.java)

    @Provides
    @Singleton
    fun provideMageCartRepositoryImpl(api: ApiInterface) = MageCartRepositoryImpl(api)

    private fun getLoggingInterceptor() = HttpLoggingInterceptor().apply {
        level = HttpLoggingInterceptor.Level.BODY
    }

    @Provides
    @Singleton
    fun provideOkHttpClient() = OkHttpClient.Builder()
        .addInterceptor(getLoggingInterceptor())
        .addInterceptor { chain ->
            val request = chain.request()

            // NOTE: When authorization was passed using @Header it was appending the two keys
            val newRequest = request.newBuilder().apply {
                if (request.header("Authorization") == null) {
                    addHeader("Authorization", "Bearer ${BuildConfig.MAGENTO_ACCESS_TOKEN}")
                }
            }.build()
            chain.proceed(newRequest)
        }
        .build()

    @Provides
    @Singleton
    fun provideRetrofitInstance(client: OkHttpClient) = Retrofit.Builder()
        .client(client)
        .baseUrl(ApiInterface.API_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .build()
}