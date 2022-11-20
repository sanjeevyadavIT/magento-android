package com.sanjeevyadavit.magecart.di

import com.sanjeevyadavit.magecart.data.repository.MageCartRepositoryImpl
import com.sanjeevyadavit.magecart.domain.repository.MageCartRepository
import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    @Singleton
    abstract fun bindMageCartRepository(
        mageCartRepositoryImpl: MageCartRepositoryImpl
    ): MageCartRepository
}