package com.jorgesanaguaray.randomgameapp.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.jorgesanaguaray.randomgameapp.domain.GameItem
import com.jorgesanaguaray.randomgameapp.domain.GetGamesFromApi
import com.jorgesanaguaray.randomgameapp.domain.GetGamesFromDatabase
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

@HiltViewModel
class MainViewModel @Inject constructor(
    private val getGamesFromApi: GetGamesFromApi,
    private val getGamesFromDatabase: GetGamesFromDatabase
    ) : ViewModel() {

    private val _gameItemModel = MutableLiveData<GameItem>()
    val gameItemModel: LiveData<GameItem> get() = _gameItemModel

    private val _message = MutableLiveData<String>()
    val message: LiveData<String> get() = _message

    private val _scrollViewVisibility = MutableLiveData<Boolean>()
    val scrollViewVisibility: LiveData<Boolean> get() = _scrollViewVisibility

    private val _textViewVisibility = MutableLiveData<Boolean>()
    val textViewVisibility: LiveData<Boolean> get() = _textViewVisibility

    private val _progressBarVisibility = MutableLiveData<Boolean>()
    val progressBarVisibility: LiveData<Boolean> get() = _progressBarVisibility

    fun getRandomGame() {

        viewModelScope.launch {

            _scrollViewVisibility.value = false
            _textViewVisibility.value = false
            _progressBarVisibility.value = true

            try {

                val games = getGamesFromApi()

                if (games.isNotEmpty()) {
                    _gameItemModel.value = games.random()
                }

                _scrollViewVisibility.value = true
                _textViewVisibility.value = false
                _progressBarVisibility.value = false

            } catch (e: Exception) {

                val games = getGamesFromDatabase()

                if (games.isNotEmpty()) {
                    _gameItemModel.value = games.random()
                    _scrollViewVisibility.value = true
                    _textViewVisibility.value = false
                    _progressBarVisibility.value = false
                } else {
                    _message.value = e.message
                    _scrollViewVisibility.value = false
                    _textViewVisibility.value = true
                    _progressBarVisibility.value = false
                }

            }

        }

    }

}