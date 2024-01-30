package tmdb.arch.movieapp.ui.screens.saved

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import androidx.navigation.fragment.navArgs
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.parameter.parametersOf
import tmdb.arch.movieapp.R
import tmdb.arch.movieapp.databinding.MoviesSavedBinding
import tmdb.arch.movieapp.ui.screens.saved.adapters.SavedMoviesAdapter
import tmdb.arch.movieapp.utils.delegates.autoNull
import tmdb.arch.movieapp.utils.delegates.viewBinding
import tmdb.arch.movieapp.utils.extensions.collectRepeatOnStart

class SavedMovies : Fragment(R.layout.movies_saved) {

    private val binding by viewBinding(MoviesSavedBinding::bind)
    private val args by navArgs<SavedMoviesArgs>()
    private val viewModel by viewModel<SavedMoviesViewModel> {
        parametersOf(args.command)
    }
    private val adapter by autoNull {
        SavedMoviesAdapter(
            onMovieClicked = {
                findNavController().navigate(SavedMoviesDirections.savedMoviesToMovieDetails(it))
            }
        )
    }
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        initViews()
        subscribeUi()
    }

    private fun initViews() {
        binding.root.adapter = adapter
    }

    private fun subscribeUi() {
        viewModel.savedMovies.collectRepeatOnStart(viewLifecycleOwner) {
            adapter.submitList(it)
        }
    }

}
