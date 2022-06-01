package com.dmitry.gamesapp.database

import androidx.lifecycle.LiveData
import androidx.room.*

//Dao - data access object
@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addGame(game: Game)

    @Update
    fun updateGame(game: Game)

    @Delete
    fun deleteGame(game: Game)

    @Query("SELECT * FROM game_table ORDER BY id ASC")
    fun readData(): LiveData<List<Game>>
}