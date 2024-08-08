package com.mc0dex.appwithroomksp.data.room.models

import androidx.annotation.WorkerThread
import androidx.lifecycle.LiveData
import kotlinx.coroutines.flow.Flow

class UserRepository(private val userDAO: UserDAO) {

    val allUsers: Flow<List<UserItem>> = userDAO.getAllUsers()

    suspend fun addUser(userItem: UserItem) {
        userDAO.insert(userItem)
    }

    suspend fun updateUser(userItem: UserItem) {
        userDAO.update(userItem)
    }

    suspend fun deleteUser(userItem: UserItem) {
        userDAO.delete(userItem)
    }

    fun getUserById(id: Int): UserItem? {
        return userDAO.getUserById(id)
    }
}