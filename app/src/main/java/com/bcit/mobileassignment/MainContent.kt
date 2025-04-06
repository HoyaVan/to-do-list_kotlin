package com.bcit.mobileassignment

import CardState
import CardStateFactory
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.bcit.mobileassignment.data.API_KEY
import com.bcit.mobileassignment.data.HandledCardRepository
import com.bcit.mobileassignment.data.HistoryRepository

@Composable
fun MainContent(repo: HandledCardRepository, eventViewModel: EventViewModel) {
    val navController = rememberNavController()
    val viewModel: CardState = viewModel(factory = CardStateFactory(repo))
    val cardList = viewModel.cards
    val currentBackStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = currentBackStackEntry?.destination?.route

    Scaffold(
        topBar = {
            MyTopBar(
                title = "To-do List",
                navController = navController,
                eventViewModel = eventViewModel
            )
        },
        bottomBar = {
            if (currentRoute == "home") {
                MyBottomBar(cardList = cardList)
            }
        }
    ) { padding ->
        NavHost(
            navController = navController,
            startDestination = "home",
            modifier = Modifier
                .fillMaxSize()
                .padding(padding)
        ) {
            composable("home") {
                Home(
                    navController = navController,
                    viewModel = viewModel
                )
            }
            composable("achievement") {
                AchievementPage(
                    navController = navController,
                    viewModel = viewModel
                )
            }
        }
    }
}
