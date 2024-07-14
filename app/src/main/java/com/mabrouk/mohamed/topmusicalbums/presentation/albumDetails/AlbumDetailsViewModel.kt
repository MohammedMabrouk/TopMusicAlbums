package com.mabrouk.mohamed.topmusicalbums.presentation.albumDetails

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mabrouk.mohamed.topmusicalbums.domain.model.AlbumItem
import com.mabrouk.mohamed.topmusicalbums.domain.usecase.GetAlbumDetailsUseCase
import com.mabrouk.mohamed.topmusicalbums.presentation.Outcome
import com.mabrouk.mohamed.topmusicalbums.utils.ErrorResolver
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class AlbumDetailsViewModel @Inject constructor(
    private val getAlbumDetailsUseCase: GetAlbumDetailsUseCase,
    private val errorResolver: ErrorResolver
) : ViewModel() {

    private val _albumDetailsOutcome = MutableStateFlow<Outcome<AlbumItem>>(Outcome.Empty())
    val albumDetailsOutcome = _albumDetailsOutcome.asStateFlow()

    fun getAlbumDetails(albumId: Long) {
        viewModelScope.launch {
            getAlbumDetailsUseCase.invoke(albumId).collect {
                if (it is Outcome.Error) {
                    _albumDetailsOutcome.value =
                        Outcome.Error(Exception(errorResolver.resolveError(it.exception.message?.toInt())))
                } else {
                    _albumDetailsOutcome.value = it
                }
            }
        }
    }
}