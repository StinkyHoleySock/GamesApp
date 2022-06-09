package com.danila.gamesapp.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.viewModelScope
import com.danila.gamesapp.database.Game
import com.danila.gamesapp.database.GameDatabase
import com.danila.gamesapp.database.GameRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

//Класс представляет собой централизованное хранилище для объектов Game
class GameListViewModel(application: Application): AndroidViewModel(application) {

    val readData: LiveData<List<Game>>
    private val repository: GameRepository

    //Инициализация данных
    init {
        val gameDao = GameDatabase.getDatabase(application).gameDao()
        repository = GameRepository(gameDao)
        readData = repository.readData
    }

    fun addGame(game: Game){
        //Запуск корутины на IO потоке
        viewModelScope.launch(Dispatchers.IO) {
            repository.addGame(game)
        }
    }

    fun updateGame(game: Game){
        viewModelScope.launch(Dispatchers.IO) {
            repository.updateGame(game)
        }
    }

    fun deleteGame(game: Game){
        viewModelScope.launch(Dispatchers.IO) {
            repository.deleteGame(game)
        }
    }
}

