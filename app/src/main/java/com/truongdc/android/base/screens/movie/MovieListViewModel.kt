package com.truongdc.android.base.screens.movie

import androidx.lifecycle.viewModelScope
import com.truongdc.android.base.base.BaseViewModel
import com.truongdc.android.base.components.state.UiStateDelegate
import com.truongdc.android.base.components.state.UiStateDelegateImpl
import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.repository.MovieRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository
) : BaseViewModel(),
    UiStateDelegate<MovieListViewModel.UiState, MovieListViewModel.Event> by UiStateDelegateImpl(
        UiState()
    ) {

    data class UiState(
        val mListMovie: List<Movie> = mutableListOf()
    )

    sealed interface Event {
    }

    fun requestMovie() {
        launchTaskSync(isLoading = true, onRequest = {
            movieRepository.getMovies()
        }, onSuccess = { mList ->
            asyncUpdateUiState(viewModelScope) { state -> state.copy(mListMovie = mList) }
        })
    }
}