package tmdb.arch.movieapp.di

import okhttp3.OkHttpClient
import okhttp3.logging.HttpLoggingInterceptor
import org.koin.dsl.module
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory
import tmdb.arch.movieapp.domain.remote.AuthInterceptor
import tmdb.arch.movieapp.domain.remote.LanguageInterceptor
import tmdb.arch.movieapp.domain.remote.MoviesService
import tmdb.arch.movieapp.domain.repository.MoviesRepository
import java.util.concurrent.TimeUnit

val remoteModule
    get() = module {
        single { MoviesRepository(moviesService) }
    }

private val loggingInterceptor
    get() = HttpLoggingInterceptor().setLevel(HttpLoggingInterceptor.Level.BODY)

private val httpClient
    get() = OkHttpClient.Builder()
        .addInterceptor(AuthInterceptor())
        .addInterceptor(LanguageInterceptor())
        .addInterceptor(loggingInterceptor)
        .callTimeout(30L, TimeUnit.SECONDS)
        .build()

private val retrofit
    get() = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .client(httpClient)
        .baseUrl("https://api.themoviedb.org/3/")
        .build()

private val moviesService get() = retrofit.create(MoviesService::class.java)
