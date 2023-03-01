package com.example.vitrolla_movil.di
import com.example.vitrolla_movil.data.SongInterfaceApi
import com.squareup.moshi.Moshi
import com.squareup.moshi.kotlin.reflect.KotlinJsonAdapterFactory
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.moshi.MoshiConverterFactory
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Singleton
    @Provides
    fun provideMoshi(): Moshi {
        return Moshi
            .Builder()
            .add(KotlinJsonAdapterFactory())
            .build()
    }

    @Singleton
    @Provides
    fun providesApi(moshi: Moshi): SongInterfaceApi {
        return Retrofit.Builder()
            .baseUrl("http://192.168.1.20:8000")
            .addConverterFactory(MoshiConverterFactory.create(moshi))
            .build()
            .create(SongInterfaceApi::class.java)
    }
}