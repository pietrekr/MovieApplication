package tmdb.arch.movieapp.ui.screens.search

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import tmdb.arch.movieapp.domain.model.Movie
import tmdb.arch.movieapp.domain.usecases.MoviesSearchInteractor
import tmdb.arch.movieapp.utils.UiState

class SearchMoviesViewModel(
    private val searchInteractor: MoviesSearchInteractor,
) : ViewModel() {

    private val _searchResults: MutableStateFlow<UiState<List<Movie>>> =
        MutableStateFlow(UiState.Loading)

    val searchResultsState: StateFlow<UiState<List<Movie>>> get() = _searchResults.asStateFlow()

    init {
        viewModelScope.launch {
            searchInteractor.searchResult
                .collect(_searchResults)
        }
    }

    fun onSearchQueryChanged(query: CharSequence) = searchInteractor.searchQuery(query)
}
