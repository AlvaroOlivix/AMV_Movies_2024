package edu.example.amv_movies_app.features.movie.presentation.adapter

import android.view.View
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.ViewHolder
import edu.example.amv_movies_app.databinding.ViewMovieItemBinding
import edu.example.amv_movies_app.features.movie.domain.Movie
import loadUrl

class MovieViewHolder(private val view: View) : RecyclerView.ViewHolder(view) {

    private lateinit var binding: ViewMovieItemBinding

    fun bind(movie: Movie, onClick: (String) -> Unit) {
        binding = ViewMovieItemBinding.bind(view)

        binding.apply {
            movieName.text = movie.title
            moviePoster.loadUrl(movie.poster)
            view.setOnClickListener {
                onClick(movie.id)
            }
        }
    }
}