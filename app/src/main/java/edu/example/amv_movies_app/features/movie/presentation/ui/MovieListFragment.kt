package edu.example.amv_movies_app.features.movie.presentation.ui

import android.content.Context
import android.os.Bundle
import android.renderscript.ScriptGroup.Binding
import android.util.AttributeSet
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import edu.example.amv_movies_app.features.movie.domain.Movie
import edu.example.amv_movies_app.features.movie.presentation.MovieFactory
import edu.example.amv_movies_app.features.movie.presentation.viewModel.MovieListViewModel

class MovieListFragment : Fragment() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel: MovieListViewModel

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return super.onCreateView(inflater, container, savedInstanceState)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieFactory = MovieFactory(requireContext())
        viewModel = movieFactory.buildMovieListViewModel()
        viewModel.loadMovieList()
        setUpObserver()

    }


    private fun setUpObserver() {
        val movieListObserver = Observer<MovieListViewModel.UiState> { uistate ->
            uistate.movies?.let {
                //bindData(it)
            }
            uistate.error?.let {
                //MuestroError(it)
            } ?: run {
                //EscondoError()
            }
            if (uistate.isLoading) {
                //Mostrar Cargando
                Log.d("@dev", "Cargando")
            } else {
                //Esconder Cargando
                Log.d("@dev", "No Cargando")
            }
        }
        viewModel.uiState.observe(viewLifecycleOwner, movieListObserver)
    }

    private fun bindData() {

    }
}