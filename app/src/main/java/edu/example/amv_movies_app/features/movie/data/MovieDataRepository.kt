package edu.example.amv_movies_app.features.movie.data

import edu.example.amv_movies_app.features.movie.data.local.MovieXmlDataSource
import edu.example.amv_movies_app.features.movie.data.remote.MovieMockRemoteDataSource
import edu.example.amv_movies_app.features.movie.domain.Movie
import edu.example.amv_movies_app.features.movie.domain.MovieRepository

class MovieDataRepository(
    private val movieRemote: MovieMockRemoteDataSource,
    private val movieXml: MovieXmlDataSource
) :
    MovieRepository {
    override fun getMovieList(): List<Movie> {
        val moviesFromLocal = movieXml.findAll()

        if (moviesFromLocal.isEmpty()) {
            val moviesFromRemote = movieRemote.getMovieList()
            movieXml.saveAll(moviesFromRemote)
            return moviesFromRemote
        } else {
            return moviesFromLocal
        }
    }

    override fun getMovie(id: String): Movie? {
        val movieFromLocal = movieXml.findById(id)
        if (movieFromLocal == null){
            movieRemote.getMovie(id)?.let {
                movieXml.saveMovie(it)
                return it
            }
        }
        return movieFromLocal


    }


}