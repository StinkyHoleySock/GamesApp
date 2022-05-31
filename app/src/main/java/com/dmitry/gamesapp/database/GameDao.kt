package com.dmitry.gamesapp.database

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query

//Dao - data access object
@Dao
interface GameDao {

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun addGame(game: Game)

    @Query("SELECT * FROM game_table ORDER BY id ASC")
    fun readData(): LiveData<List<Game>>
}