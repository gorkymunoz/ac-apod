package com.gorkymunoz.ac.apod.di

import com.gorkymunoz.ac.apod.data.database.source.APODRoomDataSource
import com.gorkymunoz.ac.apod.data.repository.APODRepository
import com.gorkymunoz.ac.apod.data.server.source.NasaAPODDataSource
import com.gorkymunoz.ac.apod.usecases.GetAPOD
import com.gorkymunoz.ac.apod.usecases.GetAPODByDate
import com.gorkymunoz.ac.apod.usecases.GetAPODByDateRange
import com.gorkymunoz.ac.apod.usecases.ToggleFavoriteAPOD
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent

@Module
@InstallIn(ViewModelComponent::class)
object DataModule {

    @Provides
    fun provideAPODRepository(
        remoteDataSource: NasaAPODDataSource,
        localDataSource: APODRoomDataSource
    ) = APODRepository(remoteDataSource, localDataSource)

    @Provides
    fun provideGetAPOD(repository: APODRepository) = GetAPOD(repository)

    @Provides
    fun provideGetAPODByDate(repository: APODRepository) = GetAPODByDate(repository)

    @Provides
    fun provideGetAPODByDateRange(repository: APODRepository) = GetAPODByDateRange(repository)

    @Provides
    fun provideToggleFavoriteAPOD(repository: APODRepository) = ToggleFavoriteAPOD(repository)

}