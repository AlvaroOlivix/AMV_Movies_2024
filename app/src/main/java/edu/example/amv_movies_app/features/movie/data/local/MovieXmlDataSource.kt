package edu.example.amv_movies_app.features.movie.data.local

import android.content.Context
import com.google.gson.Gson
import edu.example.amv_movies_app.R
import edu.example.amv_movies_app.features.movie.domain.Movie


class MovieXmlDataSource(private val contex: Context) {

    /*
    El nombre del archivo de sharedPreferences lo guardamos
    manualmente en strings.xml y lo recuperamos aquí
     */

    private val sharedPref = contex.getSharedPreferences(
        contex.getString(R.string.local_movies_xml),
        Context.MODE_PRIVATE
    )
    private val gson = Gson()

    fun saveMovie(movie: Movie) {
        val editor = sharedPref.edit()
        editor.putString(movie.id, gson.toJson(movie))
        editor.apply()
    }

    fun saveAll(movies: List<Movie>) {
        val editor = sharedPref.edit()
        movies.forEach {
            editor.putString(it.id, gson.toJson(it))
        }
        editor.apply()
    }

    fun findAll(): List<Movie> {
        val movies = mutableListOf<Movie>()
        val mapMovies = sharedPref.all

        mapMovies.values.forEach { jsonMovie ->
            val movie = gson.fromJson(jsonMovie.toString(), Movie::class.java)
            movies.add(movie)
        }
        return movies
    }

    fun findById(movieId: String): Movie? {
        return sharedPref.getString(movieId, null)?.let {
            gson.fromJson(it, Movie::class.java)
        }
    }

    fun delete() {
        sharedPref.edit().clear().apply()
    }

    fun delete(movieId: String) {
        sharedPref.edit().remove(movieId).apply()
    }
}