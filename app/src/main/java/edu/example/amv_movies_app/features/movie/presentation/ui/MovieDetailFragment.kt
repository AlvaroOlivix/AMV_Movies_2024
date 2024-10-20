package edu.example.amv_movies_app.features.movie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import edu.example.amv_movies_app.features.movie.domain.Movie
import edu.example.amv_movies_app.features.movie.presentation.MovieFactory
import edu.example.amv_movies_app.features.movie.presentation.viewModel.MovieDetailViewModel

class MovieDetailFragment: Fragment() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel: MovieDetailViewModel

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
        viewModel = movieFactory.buildMovieDetailViewModel()
        //viewModel.loadMovieDetail("1")
        setUpObserver()


    }
    private fun setUpObserver(){
        val movieDetailObserver = Observer<MovieDetailViewModel.UiState>{uistate ->
            uistate.movie?.let {
                //bindData(it)
            }
            uistate.error?.let {
                //MuestroError(it)
            } ?: run {
                //EscondoError()
            }
            if (uistate.isLoading){
                //Mostrar Cargando
                Log.d("@dev", "Cargando")
            }else{
                //Esconder Cargando
                Log.d("@dev", "No Cargando")
            }
            }
        viewModel.uiState.observe(viewLifecycleOwner, movieDetailObserver)

    }
    private fun bindData(movie: Movie){

    }
}