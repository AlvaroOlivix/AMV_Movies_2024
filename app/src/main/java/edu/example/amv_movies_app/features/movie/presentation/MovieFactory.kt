package edu.example.amv_movies_app.features.movie.presentation

import android.content.Context
import edu.example.amv_movies_app.features.movie.data.MovieDataRepository
import edu.example.amv_movies_app.features.movie.data.local.MovieXmlDataSource
import edu.example.amv_movies_app.features.movie.data.remote.MovieMockRemoteDataSource
import edu.example.amv_movies_app.features.movie.domain.GetMovieListUseCase
import edu.example.amv_movies_app.features.movie.domain.GetMovieUseCase
import edu.example.amv_movies_app.features.movie.presentation.viewModel.MovieDetailViewModel
import edu.example.amv_movies_app.features.movie.presentation.viewModel.MovieListViewModel

class MovieFactory(private val context: Context) {
    private val movieRemote = MovieMockRemoteDataSource()
    private val movieLocal = MovieXmlDataSource(context)
    private val movieDataRepository = MovieDataRepository(movieRemote, movieLocal)
    private val getMovieUseCase = GetMovieUseCase(movieDataRepository)
    private val getMovieListUseCase = GetMovieListUseCase(movieDataRepository)


    fun buildMovieListViewModel(): MovieListViewModel {
        return MovieListViewModel(getMovieListUseCase)
    }

    fun buildMovieDetailViewModel(): MovieDetailViewModel {
        return MovieDetailViewModel(getMovieUseCase)


    }
}