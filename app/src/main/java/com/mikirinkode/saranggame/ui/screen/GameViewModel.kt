package com.mikirinkode.saranggame.ui.screen

import android.util.Log
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.State
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
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

//    private val _isShowGameList: MutableStateFlow<Boolean> = MutableStateFlow(false)
//    val isShowGameList: StateFlow<Boolean> get() = _isShowGameList


    // STATE FOR LIST_AND_DETAIL LAYOUT
    private val _isShowGameDetail: MutableState<Boolean> = mutableStateOf(false)
    val isShowGameDetail: Boolean get() = _isShowGameDetail.value

    // STATE FOR LIST ONLY LAYOUT
    private val _currentScreen: MutableState<GameScreenType> = mutableStateOf(GameScreenType.GENRE_LIST)
    val currentScreen: GameScreenType get() = _currentScreen.value

    fun showGameDetail() {
        _isShowGameDetail.value = true
    }

    fun hideGameDetail() {
        _isShowGameDetail.value = false
    }

    private val _genreId: MutableState<Int> = mutableIntStateOf(-1)
    val genreId: Int get() = _genreId.value

    private val _gameId: MutableState<Int> = mutableIntStateOf(-1)
    val gameId: Int get() = _gameId.value

    fun onBackClicked(){
        when(currentScreen){
            GameScreenType.GENRE_LIST -> {}
            GameScreenType.GAME_LIST -> {
                _currentScreen.value = GameScreenType.GENRE_LIST
            }
            GameScreenType.GAME_DETAIL -> {
                _currentScreen.value = GameScreenType.GAME_LIST
            }
        }
    }

    fun onGenreClicked(newGenreId: Int) {
        _isShowGameDetail.value = false
        _currentScreen.value = GameScreenType.GAME_LIST

        _genreId.value = newGenreId
        getGameListByGenreId(genreId)
    }

    fun onGameClicked(newGameId: Int){
        _isShowGameDetail.value = true
        _currentScreen.value = GameScreenType.GAME_DETAIL

        _gameId.value = newGameId
        getGameDetail(newGameId)
    }

    fun getGenreList() {
        viewModelScope.launch {
            _genreListState.value = UiState.Loading

            try {
                val result = gameRepository.getGenreList()
                Log.e(TAG, "on finish get genre List")
                Log.e(TAG, "genre id: ${genreId}")
                if (genreId == -1) {
                    _genreId.value = result.first().id ?: 4
                    getGameListByGenreId(genreId)
                }
                _genreListState.value = UiState.Success(result)
            } catch (e: Exception) {
                _genreListState.value = UiState.Error("Error: ${e.message}")
            }
        }
    }

    fun getGameListByGenreId(genreId: Int) {
        Log.e(TAG, "getGameListByGenreId called")
        Log.e(TAG, "getGameListByGenreId genre id: ${genreId}")
        viewModelScope.launch {
            _gameListState.value = UiState.Loading

            try {
                val result = gameRepository.getGameList(genreId.toString())
                _gameListState.value = UiState.Success(result)
            } catch (e: Exception) {
                _gameListState.value = UiState.Error("Error: ${e.message}")
            }
        }
    }

    fun getGameDetail(gameId: Int) {
        viewModelScope.launch {
            _gameState.value = UiState.Loading

            try {
                val result = gameRepository.getGameDetail(gameId.toString())
                _gameState.value = UiState.Success(result)
            } catch (e: Exception) {
                _gameState.value = UiState.Error("Error: ${e.message}")
            }
        }
    }

    companion object {
        private const val TAG = "GameViewModel"
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

enum class GameScreenType {
    GENRE_LIST, GAME_LIST, GAME_DETAIL
}