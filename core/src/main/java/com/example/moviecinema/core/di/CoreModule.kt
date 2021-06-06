package com.example.moviecinema.core.di

import androidx.room.Room
import com.example.moviecinema.core.BuildConfig
import com.example.moviecinema.core.data.source.remote.network.ApiService
import com.example.moviecinema.core.domain.repository.IMovieCinemaRepository
import com.example.moviecinema.data.MovieCinemaRepository
import com.example.moviecinema.data.source.local.LocalDataSource
import com.example.moviecinema.data.source.local.room.MovieCinemaDatabase
import com.example.moviecinema.data.source.remote.RemoteDataSource
import com.example.moviecinema.utils.AppExecutors
import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.android.ext.koin.androidContext
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import java.util.concurrent.TimeUnit

val databaseModule = module {
    factory { get<MovieCinemaDatabase>().movieCinemaDao() }
    single {
        Room.databaseBuilder(
            androidContext(),
            MovieCinemaDatabase::class.java, "movie_entities"
        ).fallbackToDestructiveMigration().build()
    }
}

val networkModule = module {
    single {
        OkHttpClient.Builder()
            .addInterceptor(HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY))
            .connectTimeout(120, TimeUnit.SECONDS)
            .readTimeout(120, TimeUnit.SECONDS)
            .build()
    }
    single {
        val retrofit = Retrofit.Builder()
            .baseUrl(BuildConfig.BASE_URL)
            .addConverterFactory(GsonConverterFactory.create())
            .client(get())
            .build()
        retrofit.create(ApiService::class.java)
    }
}

val repositoryModule = module {
    single { LocalDataSource(get()) }
    single { RemoteDataSource(get()) }
    single { AppExecutors() }
    single<IMovieCinemaRepository> {
        MovieCinemaRepository(
            get(),
            get(),
            get()
        )
    }
}