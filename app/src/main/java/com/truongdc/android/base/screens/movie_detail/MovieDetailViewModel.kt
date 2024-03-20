package com.truongdc.android.base.screens.movie_detail

import androidx.lifecycle.viewModelScope
import com.truongdc.android.base.base.BaseViewModel
import com.truongdc.android.base.components.state.UiStateDelegate
import com.truongdc.android.base.components.state.UiStateDelegateImpl
import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MovieDetailViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel(),
    UiStateDelegate<MovieDetailViewModel.UiState, MovieDetailViewModel.Event> by UiStateDelegateImpl(
        UiState()
    ) {

    data class UiState(
        val movie: Movie? = null
    )

    sealed interface Event {
        data object BackToList : Event
    }

    fun requestMovie(movieId: Int) {
        launchTaskSync(isLoading = true, onRequest = {
            movieRepository.getDetailMovies(movieId)
        }, onSuccess = { movie ->
            asyncUpdateUiState(viewModelScope) { state -> state.copy(movie = movie) }
        })
    }

    fun sendEvents(event: Event) = viewModelScope.launch { sendEvent(event) }
}
