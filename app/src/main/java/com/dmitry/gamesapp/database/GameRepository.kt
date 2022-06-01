package com.dmitry.gamesapp.database

import androidx.lifecycle.LiveData

class GameRepository(private val gameDao: GameDao) {

    val readData: LiveData<List<Game>> = gameDao.readData()

    fun addGame(game: Game){
        gameDao.addGame(game)
    }

    fun updateGame(game: Game){
        gameDao.updateGame(game)
    }

    fun deleteGame(game: Game){
        gameDao.deleteGame(game)
    }
}


