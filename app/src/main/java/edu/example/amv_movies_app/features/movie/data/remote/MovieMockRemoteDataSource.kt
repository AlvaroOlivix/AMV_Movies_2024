package edu.example.amv_movies_app.features.movie.data.remote

import edu.example.amv_movies_app.features.movie.domain.Movie

class MovieMockRemoteDataSource {

    private val movieList = listOf(
        Movie(
            "1",
            "The Batman",
            "The Batman is a 2022 American superhero film based on the DC Comics character Batman.",
            "https://m.media-amazon.com/images/M/MV5BMDdmMTBiNTYtMDIzNi00NGVlLWIzMDYtZTk3MTQ3NGQxZGEwXkEyXkFqcGdeQXVyMzMwOTU5MDk@"
        ),

        Movie(
            "2",
            "DeadPool",
            "DeadPool is a 2021 American superhero film based on the Marvel Comics character DeadPool.",
            ""
        ),

        Movie(
            "3",
            "The MarrowBorne Secret",
            "The MarrowBorne Secret is an Spanish movie filmed on Asturias in the 2017",
            ""
        )
    )

    fun getMovieList(): List<Movie> {
        return movieList
    }

    fun getMovie(id: String): Movie? {
        return movieList.firstOrNull { it.id == id }
    }
}