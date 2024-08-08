package com.mc0dex.appwithroomksp.data.navigations

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.mc0dex.appwithroomksp.UserViewModel
import com.mc0dex.appwithroomksp.data.room.models.UserItem
import com.mc0dex.appwithroomksp.ui.screens.AddUserScreen
import com.mc0dex.appwithroomksp.ui.screens.DashboardScreen

@Composable
fun NavigationSetup(userViewModel: UserViewModel) {
    val navController = rememberNavController()
    NavHost(navController, startDestination = "dashboard") {
        // Dashboard route
        composable("dashboard") {
            DashboardScreen(navController, userViewModel)
        }

        // AddUser route
        composable("addUser") {
            AddUserScreen(navController, userViewModel, userToEdit = null)
        }

        // EditUser route
        composable("editUser/{userId}") { backStackEntry ->
            val userId = backStackEntry.arguments?.getString("userId")?.toIntOrNull()
            var userToEdit by remember { mutableStateOf<UserItem?>(null) }

            LaunchedEffect(userId) {
                if (userId != null) {
                    userViewModel.getUserById(userId) {
                        userToEdit = it
                    }
                }
            }

            if (userToEdit != null) {
                AddUserScreen(navController, userViewModel, userToEdit)
            }
        }
    }
}