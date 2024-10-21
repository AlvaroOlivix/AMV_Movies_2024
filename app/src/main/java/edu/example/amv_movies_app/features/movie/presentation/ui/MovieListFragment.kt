package edu.example.amv_movies_app.features.movie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import edu.example.amv_movies_app.databinding.FragmentMovieListBinding
import edu.example.amv_movies_app.features.movie.presentation.MovieFactory
import edu.example.amv_movies_app.features.movie.presentation.viewModel.MovieListViewModel

class MovieListFragment : Fragment() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel: MovieListViewModel

    private var _binding: FragmentMovieListBinding? = null
    val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        movieFactory = MovieFactory(requireContext())
        viewModel = movieFactory.buildMovieListViewModel()
        setUpObserver()
        viewModel.loadMovieList()

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
        //Continuación del Recycler, con Adapter
    }

    private fun navigateToDetail(id: String) {
        findNavController().navigate(
            MovieListFragmentDirections.fragmentMovieListToFragmentMovieDetail(
                id
            )
        )
    }
}