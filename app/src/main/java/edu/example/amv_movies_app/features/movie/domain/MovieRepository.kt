package edu.example.amv_movies_app.features.movie.domain

interface MovieRepository {

    fun getMovieList(): List<Movie>
    fun getMovie(id: String): Movie?
}