package com.hemanth.idrive.di

import com.hemanth.idrive.data.RepoService
import com.hemanth.idrive.data.repository.HomeRepository
import com.hemanth.idrive.data.repositoryImpl.HomeRepositoryImpl
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ApplicationComponent

@Module
@InstallIn(ApplicationComponent::class)
class RepositoryModule {

    @Provides
    fun provideHomeRepo(repoService: RepoService): HomeRepository =
        HomeRepositoryImpl(repoService)

}