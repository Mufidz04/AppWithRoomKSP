package com.mc0dex.appwithroomksp.data.room.models

import androidx.lifecycle.LiveData
import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface UserDAO {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(userItem: UserItem)

    @Update(onConflict = OnConflictStrategy.REPLACE)
    suspend fun update(userItem: UserItem)

    @Delete()
    suspend fun delete(userItem: UserItem)

    @Query("SELECT * FROM users")
    fun getAllUsers(): Flow<List<UserItem>>

    @Query("SELECT * FROM users WHERE id = :id")
    fun getUserById(id: Int): UserItem?
}

