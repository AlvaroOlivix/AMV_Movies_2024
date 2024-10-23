package edu.example.amv_movies_app.features.movie.presentation.ui

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import edu.example.amv_movies_app.databinding.FragmentMovieListBinding
import edu.example.amv_movies_app.features.movie.domain.Movie
import edu.example.amv_movies_app.features.movie.presentation.MovieFactory
import edu.example.amv_movies_app.features.movie.presentation.adapter.MovieAdapter
import edu.example.amv_movies_app.features.movie.presentation.viewModel.MovieListViewModel

class MovieListFragment : Fragment() {

    private lateinit var movieFactory: MovieFactory
    private lateinit var viewModel: MovieListViewModel

    private var _binding: FragmentMovieListBinding? = null
    private val binding get() = _binding!!

    private val movieAdapter = MovieAdapter()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieListBinding.inflate(inflater, container, false)
        setUpView()
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
        viewModel.uiState.observe(viewLifecycleOwner, movieListObserver)
    }

    private fun setUpView() {
        binding.apply {
            listMovies.layoutManager =
                LinearLayoutManager(
                    context,
                    LinearLayoutManager.VERTICAL,
                    false
                )
            movieAdapter.setOnclickListener { id ->
                navigateToDetail(id)
            }
            listMovies.adapter = movieAdapter
        }
    }

    private fun bindData(movieList: List<Movie>) {
        movieAdapter.submitList(movieList)
    }

    private fun navigateToDetail(id: String) {
        findNavController().navigate(
            MovieListFragmentDirections.actionFromMovieListToMovieDetail(id)

        )
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
