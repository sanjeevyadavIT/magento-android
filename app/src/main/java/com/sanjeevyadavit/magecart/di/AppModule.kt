package com.sanjeevyadavit.magecart.di

import com.sanjeevyadavit.magecart.data.remote.ApiInterface
import com.sanjeevyadavit.magecart.data.repository.MageCartRepositoryImpl
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Singleton
    fun provideApiInterface(): ApiInterface {
        return RetrofitClient.getInstance()
    }

    @Provides
    @Singleton
    fun provideMageCartRepository(api: ApiInterface): MageCartRepository {
        return MageCartRepositoryImpl(api)
    }
}