package com.mikirinkode.saranggame.ui.screen

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.initializer
import androidx.lifecycle.viewmodel.viewModelFactory
import com.mikirinkode.saranggame.BaseApplication
import com.mikirinkode.saranggame.data.GameRepository
import com.mikirinkode.saranggame.data.RepositoryInterface
import com.mikirinkode.saranggame.data.response.Game
import com.mikirinkode.saranggame.data.response.Genre
import com.mikirinkode.saranggame.utils.UiState
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class GameViewModel(
    private val gameRepository: RepositoryInterface
) : ViewModel() {

    private val _genreListState: MutableStateFlow<UiState<List<Genre>>> =
        MutableStateFlow(UiState.Loading)
    val genreListState: StateFlow<UiState<List<Genre>>> get() = _genreListState


    private val _gameListState: MutableStateFlow<UiState<List<Game>>> =
        MutableStateFlow(UiState.Loading)
    val gameListState: StateFlow<UiState<List<Game>>> get() = _gameListState


    private val _gameState: MutableStateFlow<UiState<Game>> =
        MutableStateFlow(UiState.Loading)
    val gameState: StateFlow<UiState<Game>> get() = _gameState


    fun getGenreList() {
        viewModelScope.launch {
            _genreListState.value = UiState.Loading

            try {
                val result = gameRepository.getGenreList()
                _genreListState.value = UiState.Success(result)
            } catch (e: Exception) {
                _genreListState.value = UiState.Error("Error: ${e.message}")
            }
        }
    }

    companion object {
        val Factory: ViewModelProvider.Factory = viewModelFactory {
            initializer {
                val application =
                    (this[ViewModelProvider.AndroidViewModelFactory.APPLICATION_KEY] as BaseApplication)
                val gameRepository = application.container.gameRepository
                GameViewModel(gameRepository = gameRepository)
            }
        }
    }
}