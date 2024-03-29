package com.truongdc.android.base.screens.movie

import androidx.lifecycle.viewModelScope
import androidx.paging.PagingData
import com.truongdc.android.base.base.BaseViewModel
import com.truongdc.android.base.components.state.UiStateDelegate
import com.truongdc.android.base.components.state.UiStateDelegateImpl
import com.truongdc.android.core.model.Movie
import com.truongdc.android.core.repository.MovieRepository
import com.truongdc.android.core.source.local.datastores.PreferencesDataStore
import com.truongdc.android.core.source.local.datastores.UserDataStore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.launch
import okhttp3.internal.wait
import javax.inject.Inject

@HiltViewModel
class MovieListViewModel @Inject constructor(
    private val movieRepository: MovieRepository,
    private val userDataStore: UserDataStore,
    private val preferencesDataStore: PreferencesDataStore
) : BaseViewModel(),
    UiStateDelegate<MovieListViewModel.UiState, MovieListViewModel.Event> by UiStateDelegateImpl(
        UiState()
    ) {

    data class UiState(
        val flowPagingMovie: Flow<PagingData<Movie>>? = null
    )

    sealed interface Event {
        data object LogOutSuccess : Event

        data object LogOutFailed : Event
    }

    fun requestMovie() {
        launchTaskSync(isLoading = true, onRequest = {
            movieRepository.getMovies()
        }, onSuccess = { mFlowPagingMovie ->
            asyncUpdateUiState(viewModelScope) { state -> state.copy(flowPagingMovie = mFlowPagingMovie) }
        })
    }

    fun onHandleLogOut() = viewModelScope.launch {
        try {
            showLoading()
            delay(2000)
            userDataStore.clearAll()
            preferencesDataStore.setIsLogIn(false)
            sendEvent(Event.LogOutSuccess)
            hideLoading()
        } catch (e: Exception) {
            e.printStackTrace()
            hideLoading()
            sendEvent(Event.LogOutFailed)
        }
    }
}

