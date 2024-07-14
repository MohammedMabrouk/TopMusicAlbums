package com.mabrouk.mohamed.topmusicalbums.presentation.home

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.domain.usecase.GetTopAlbumsUseCase
import com.mabrouk.mohamed.topmusicalbums.presentation.Outcome
import com.mabrouk.mohamed.topmusicalbums.utils.ErrorResolver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumsListViewModel @Inject constructor(
    private val getTopAlbumsUseCase: GetTopAlbumsUseCase,
    private val errorResolver: ErrorResolver
) : ViewModel() {
    init {
        getAlbums()
    }

    private val _albumsOutcome = MutableStateFlow<Outcome<List<AlbumItem>>>(Outcome.Empty())
    val albumsOutcome = _albumsOutcome.asStateFlow()

    fun getAlbums() {
        viewModelScope.launch {
            getTopAlbumsUseCase.invoke().collect {
                if (it is Outcome.Error) {
                    _albumsOutcome.value =
                        Outcome.Error(Exception(errorResolver.resolveError(it.exception.message?.toInt())))
                } else {
                    _albumsOutcome.value = it
                }
            }
        }
    }
}