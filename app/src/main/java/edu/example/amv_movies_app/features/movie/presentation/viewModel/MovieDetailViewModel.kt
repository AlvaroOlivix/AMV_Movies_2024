package edu.example.amv_movies_app.features.movie.presentation.viewModel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import edu.example.amv_movies_app.app.domain.ErrorApp
import edu.example.amv_movies_app.features.movie.domain.GetMovieUseCase
import edu.example.amv_movies_app.features.movie.domain.Movie
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class MovieDetailViewModel(
    private val getMovieUseCase: GetMovieUseCase
) : ViewModel() {

    private val _uiState = MutableLiveData<UiState>()
    val uiState: LiveData<UiState> = _uiState

    fun loadMovieDetail(id: String) {
        _uiState.value = UiState(isLoading = true)
        viewModelScope.launch(Dispatchers.IO) {
            val movie = getMovieUseCase.invoke(id)
            _uiState.postValue(UiState(movie = movie))
        }
    }


    data class UiState(
        val isLoading: Boolean = false,
        val movie: Movie? = null,
        val error: ErrorApp? = null
    )

}