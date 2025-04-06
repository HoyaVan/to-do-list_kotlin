package com.bcit.mobileassignment

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcit.mobileassignment.data.HistoryRepository
import com.bcit.mobileassignment.data.client
import kotlinx.coroutines.launch
import java.time.LocalDate

class EventViewModel : ViewModel() {
    val eventText = mutableStateOf<String?>(null)

    fun fetchTodayEvents() {
        val today = LocalDate.now()
        viewModelScope.launch {
            val result = EventHelper.getFormattedEvents(today.monthValue, today.dayOfMonth)
            eventText.value = result ?: "No events found."
        }
    }
}

object EventHelper {
    suspend fun getFormattedEvents(monthValue: Int, dayOfMonth: Int): String? {
        return try {
            val events = HistoryRepository(client).getHistoryEvent(monthValue, dayOfMonth)

            println("Fetched ${events.size} events for $monthValue/$dayOfMonth")

            if (events.isEmpty()) return null

            val randomEvent = events.random()

            "• (${randomEvent.year}) ${randomEvent.event}"
        } catch (e: Exception) {
            println("Error fetching events: ${e.message}")
            null
        }
    }
}
