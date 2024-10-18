package edu.example.amv_movies_app.features.movie.data

import edu.example.amv_movies_app.features.movie.data.remote.MovieMockRemoteDataSource
import edu.example.amv_movies_app.features.movie.domain.Movie
import edu.example.amv_movies_app.features.movie.domain.MovieRepository

class MovieDataRepository(private val movieRemoteDataSource: MovieMockRemoteDataSource) : MovieRepository{
    override fun getMovieList(): List<Movie> {
        return movieRemoteDataSource.getMovieList()
    }

    override fun getMovie(id: String): Movie {
        return movieRemoteDataSource.getMovie(id)!!
    }


}