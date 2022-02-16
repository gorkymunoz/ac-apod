package com.gorkymunoz.ac.apod.di

import android.content.Context
import com.gorkymunoz.ac.apod.R
import com.gorkymunoz.ac.apod.data.server.api.APODApi
import com.gorkymunoz.ac.apod.framework.dispatcherprovider.DispatcherProvider
import com.gorkymunoz.ac.apod.framework.dispatcherprovider.StandardDispatchers
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import javax.inject.Named
import javax.inject.Singleton

@InstallIn(SingletonComponent::class)
@Module(includes = [DispatcherModule::class])
object AppModule {

    private const val baseURL = "https://api.nasa.gov/"

    @Provides
    @Singleton
    @Named("apiKey")
    fun provideApiKey(@ApplicationContext app: Context): String = app.getString(R.string.api_key)

    @Provides
    @Singleton
    fun providesAPODService(retrofit: Retrofit): APODApi {
        return retrofit.create(APODApi::class.java)
    }

    @Provides
    @Singleton
    fun provideRetrofitClient(client: OkHttpClient): Retrofit {
        return Retrofit.Builder()
            .baseUrl(baseURL)
            .client(client)
            .addConverterFactory(GsonConverterFactory.create())
            .build()
    }

    @Provides
    @Singleton
    fun provideHttpClient(): OkHttpClient {
        return HttpLoggingInterceptor().run {
            level = HttpLoggingInterceptor.Level.BODY
            OkHttpClient.Builder().addInterceptor(this).build()
        }
    }
}

@InstallIn(SingletonComponent::class)
@Module
abstract class DispatcherModule {

    @Binds
    abstract fun bindDispatchers(impl: StandardDispatchers): DispatcherProvider
}