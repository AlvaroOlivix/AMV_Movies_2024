package edu.example.amv_movies_app.features.movie.domain

class GetMovieUseCase(private val movieRepository: MovieRepository) {

    operator fun invoke(id: String): Movie {
        return movieRepository.getMovie(id)
    }

}