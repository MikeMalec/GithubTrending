package com.example.githubtrending.di

import com.example.business.data.preferences.AppPreferences
import com.example.framework.utils.Constants
import com.example.githubtrending.data.preferences.FakeAppPreferences
import dagger.Module
import dagger.Provides
import dagger.hilt.components.SingletonComponent
import dagger.hilt.testing.TestInstallIn
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers
import javax.inject.Named
import javax.inject.Singleton

@Module
@TestInstallIn(
    replaces = [AppModule::class],
    components = [SingletonComponent::class]
)
object TestAppModule {
    @Named(Constants.IO_DISPATCHER)
    @Singleton
    @Provides
    fun provideDispatcher(): CoroutineDispatcher {
        return Dispatchers.Main
    }

    @Singleton
    @Provides
    fun provideAppPreferences(): AppPreferences {
        return FakeAppPreferences()
    }
}