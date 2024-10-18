package edu.example.amv_movies_app.features.movie.domain

class GetMovieListUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(): List<Movie> {
        return movieRepository.getMovieList()
    }
}