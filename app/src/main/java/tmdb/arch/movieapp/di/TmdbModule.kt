package tmdb.arch.movieapp.di

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import tmdb.arch.movieapp.domain.repository.MoviesRepository
import tmdb.arch.movieapp.domain.usecases.GetLatestMoviesUseCase
import tmdb.arch.movieapp.ui.screens.details.MovieDetailsViewModel
import tmdb.arch.movieapp.ui.screens.discover.DiscoverMoviesViewModel
import tmdb.arch.movieapp.ui.screens.saved.SavedMoviesViewModel
import tmdb.arch.movieapp.ui.screens.search.SearchMoviesViewModel

val viewModels = module {
    viewModel { DiscoverMoviesViewModel(getLatestMoviesUseCase = get<GetLatestMoviesUseCase>()) }
    viewModel { MovieDetailsViewModel() }
    viewModel { SearchMoviesViewModel() }
    viewModel { SavedMoviesViewModel() }
}

val useCases = module {
    factory { GetLatestMoviesUseCase(repository = get<MoviesRepository>()) }
}
