package edu.example.amv_movies_app.features.movie.presentation.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.ListAdapter
import edu.example.amv_movies_app.R
import edu.example.amv_movies_app.features.movie.domain.Movie

class MovieAdapter : ListAdapter<Movie, MovieViewHolder>(MovieDiffUtil()) {

    private lateinit var onClick: (String) -> Unit

    fun setOnclickListener(onClick: (id: String) -> Unit) {
        this.onClick = onClick
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MovieViewHolder {
        val view =
            LayoutInflater.from(parent.context).inflate(R.layout.view_movie_item, parent, false)
        return MovieViewHolder(view)

    }

    override fun onBindViewHolder(holder: MovieViewHolder, position: Int) {
        holder.bind(currentList[position], onClick)
    }
}