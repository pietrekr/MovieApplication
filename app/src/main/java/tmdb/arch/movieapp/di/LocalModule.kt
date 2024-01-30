package tmdb.arch.movieapp.di

import android.content.Context
import androidx.room.Room
import org.koin.dsl.module
import tmdb.arch.movieapp.domain.local.MoviesDatabase
import tmdb.arch.movieapp.domain.local.converter.StringListConverter

val localModule
    get() = module {
        single { createMovieDatabase(get<Context>()) }
    }

private fun createMovieDatabase(context: Context) =
    Room.databaseBuilder(context, MoviesDatabase::class.java, "movies_db")
        .addTypeConverter(StringListConverter())
        .build()
        .movies
