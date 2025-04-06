package com.bcit.mobileassignment

import CardState
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.Sort
import androidx.compose.material.icons.filled.Refresh
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController


@Composable
fun AchievementPage(
    navController: NavController,
    viewModel: CardState
) {
    val achievements = viewModel.achievedCards

    Scaffold(
        bottomBar = {
            BottomAppBar {
                Text(
                    text = "Total: ${achievements.size} Achievements",
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 12.dp)
                )
                IconButton(onClick = {
                    viewModel.clearAchievements()
                }) {
                    Icon(Icons.Default.Refresh, contentDescription = "Clear All Achievements")
                }
            }
        }
    ) { paddingValues ->
        LazyColumn(modifier = Modifier.padding(paddingValues)) {
            items(achievements) { card ->
                Card(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                ) {
                    Text(
                        text = card.text,
                        modifier = Modifier.padding(16.dp)
                    )
                }
            }
        }
    }
}
