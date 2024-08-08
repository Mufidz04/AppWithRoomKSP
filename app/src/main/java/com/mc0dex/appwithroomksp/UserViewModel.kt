package com.mc0dex.appwithroomksp

import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import androidx.lifecycle.viewModelScope
import com.mc0dex.appwithroomksp.data.room.models.UserDatabase
import com.mc0dex.appwithroomksp.data.room.models.UserItem
import com.mc0dex.appwithroomksp.data.room.models.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class UserViewModel(private val repository: UserRepository) : ViewModel() {

    val allUsers = repository.allUsers

    fun insert(user: UserItem) = viewModelScope.launch {
        repository.addUser(user)
    }

    fun delete(user: UserItem) = viewModelScope.launch {
        repository.deleteUser(user)
    }

    fun update(user: UserItem) = viewModelScope.launch {
        repository.updateUser(user)
    }

    fun getUserById(userId: Int, onResult: (UserItem?) -> Unit) {
        viewModelScope.launch {
            val user = withContext(Dispatchers.IO) {
                repository.getUserById(userId)
            }
            onResult(user)
        }
    }
}

class UserViewModelFactory(private val repository: UserRepository) : ViewModelProvider.Factory {
    @Suppress("UNCHECKED_CAST")
    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(UserViewModel::class.java)) {
            return UserViewModel(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
