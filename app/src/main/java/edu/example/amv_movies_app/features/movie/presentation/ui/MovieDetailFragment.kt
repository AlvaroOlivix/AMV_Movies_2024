package edu.example.amv_movies_app.features.movie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.navArgs
import edu.example.amv_movies_app.databinding.FragmentMovieDetailBinding
import edu.example.amv_movies_app.features.movie.domain.Movie
import edu.example.amv_movies_app.features.movie.presentation.MovieFactory
import edu.example.amv_movies_app.features.movie.presentation.viewModel.MovieDetailViewModel
import loadUrl

class MovieDetailFragment : Fragment() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel: MovieDetailViewModel

    private var _binding: FragmentMovieDetailBinding? = null
    private val binding get() = _binding!!

    private val movieArgs: MovieDetailFragmentArgs by navArgs()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
       _binding = FragmentMovieDetailBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieFactory = MovieFactory(requireContext())
        viewModel = movieFactory.buildMovieDetailViewModel()
        setUpObserver()
        getMovieId()?.let {
            viewModel.loadMovieDetail(it)
        }
    }

    private fun setUpObserver() {
        val movieDetailObserver = Observer<MovieDetailViewModel.UiState> { uistate ->
            uistate.movie?.let {
                bindData(it)
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
        viewModel.uiState.observe(viewLifecycleOwner, movieDetailObserver)

    }

    private fun bindData(movie: Movie) {

        binding.apply {
            nameMovie.text = movie.title
            desc.text = movie.desc
            posterMovie.loadUrl(movie.poster)
        }
    }
    private fun getMovieId(): String? {
        return movieArgs.id
    }
}