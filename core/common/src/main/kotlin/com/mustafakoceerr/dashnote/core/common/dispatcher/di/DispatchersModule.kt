package com.mustafakoceerr.dashnote.core.common.dispatcher.di

import com.mustafakoceerr.dashnote.core.common.dispatcher.DashNoteDispatchers.Default
import com.mustafakoceerr.dashnote.core.common.dispatcher.DashNoteDispatchers.IO
import com.mustafakoceerr.dashnote.core.common.dispatcher.Dispatcher
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import kotlinx.coroutines.CoroutineDispatcher
import kotlinx.coroutines.Dispatchers

@Module
@InstallIn(SingletonComponent::class)
object DispatchersModule {
    @Provides
    @Dispatcher(IO)
    fun providesIODispatcher(): CoroutineDispatcher = Dispatchers.IO

    @Provides
    @Dispatcher(Default)
    fun providesDefaultDispatcher(): CoroutineDispatcher = Dispatchers.Default
}