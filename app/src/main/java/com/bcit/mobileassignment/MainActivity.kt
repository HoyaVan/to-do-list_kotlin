package com.bcit.mobileassignment

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.runtime.remember
import com.bcit.mobileassignment.data.CardDatabase
import com.bcit.mobileassignment.data.HandledCardRepository

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()

        val dao = CardDatabase.getDatabase(applicationContext).handledCardDao()
        val repo = HandledCardRepository(dao)

        setContent {
            val eventViewModel = remember { EventViewModel() }
            MainContent(repo = repo, eventViewModel = eventViewModel)
        }
    }
}
