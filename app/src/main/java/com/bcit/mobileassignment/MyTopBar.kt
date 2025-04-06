package com.bcit.mobileassignment

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.CheckCircle
import androidx.compose.material.icons.filled.TypeSpecimen
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import kotlinx.coroutines.delay


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MyTopBar(title: String, navController: NavController, eventViewModel: EventViewModel) {
    var isPopupVisible by remember { mutableStateOf(false) }
    val eventText = eventViewModel.eventText.value

    CenterAlignedTopAppBar(
        title = {
            Text(
                text = title,
                modifier = Modifier.clickable {
                    navController.navigate("home") {
                        popUpTo("home") { inclusive = true }
                    }
                }
            )
        },
        navigationIcon = {
            IconButton(onClick = {
                eventViewModel.fetchTodayEvents()
                isPopupVisible = true
            }) {
                Icon(Icons.Default.TypeSpecimen, contentDescription = "events in history")
            }

            if (isPopupVisible) {
                LaunchedEffect(Unit) {
                    delay(3000)
                    isPopupVisible = false
                }
            }
        },
        actions = {
            IconButton(onClick = {
                navController.navigate("achievement")
            }) {
                Icon(Icons.Default.CheckCircle, contentDescription = "Achievements")
            }
        }
    )

    // dropdown with event content
    DropdownMenu(
        expanded = isPopupVisible,
        onDismissRequest = { isPopupVisible = false }
    ) {
        Text(
            text = eventText ?: "Loading...",
            fontSize = 16.sp,
            modifier = Modifier.padding(16.dp)
        )
    }
}
