package edu.example.amv_movies_app.features.movie.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.example.amv_movies_app.app.domain.ErrorApp
import edu.example.amv_movies_app.features.movie.domain.GetMovieListUseCase
import edu.example.amv_movies_app.features.movie.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieListViewModel(
    private val getMovieListUseCase: GetMovieListUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState


    fun loadMovieList() {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val movies = getMovieListUseCase.invoke()
            _uiState.postValue(UiState(movies = movies))
        }
    }


    data class UiState(
        val isLoading: Boolean = false,
        val movies: List<Movie>? = null,
         val error: ErrorApp? = null
    )


}