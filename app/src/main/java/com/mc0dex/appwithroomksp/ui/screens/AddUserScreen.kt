package com.mc0dex.appwithroomksp.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Button
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.mc0dex.appwithroomksp.UserViewModel
import com.mc0dex.appwithroomksp.data.room.models.UserItem

@Composable
fun AddUserScreen(
    navController: NavController,
    userViewModel: UserViewModel,
    userToEdit: UserItem?
) {
    val (name, setName) = remember { mutableStateOf(userToEdit?.name ?: "") }
    val (age, setAge) = remember { mutableStateOf(userToEdit?.age?.toString() ?: "") }

    Column(
        modifier = Modifier.padding(16.dp)
    ) {
        OutlinedTextField(
            value = name,
            onValueChange = setName,
            label = { Text("Name") }
        )
        OutlinedTextField(
            value = age,
            onValueChange = setAge,
            label = { Text("Age") }
        )
        Button(
            onClick = {
                val user = UserItem(
                    id = userToEdit?.id ?: 0,
                    name = name,
                    age = age.toIntOrNull() ?: 0
                )
                if (userToEdit == null) {
                    userViewModel.insert(user)
                } else {
                    userViewModel.update(user)
                }
                navController.popBackStack()
            }
        ) {
            Text(if (userToEdit == null) "Save" else "Update")
        }
    }
}