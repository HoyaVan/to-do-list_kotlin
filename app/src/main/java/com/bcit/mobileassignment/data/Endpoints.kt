package com.bcit.mobileassignment.data;

const val BASE_URL = "https://api.api-ninjas.com/v1"
const val HISTORY_EVENTS = "$BASE_URL/historicalevents"
fun fieldsUrl(month: Int, day: Int): String {
    return "$HISTORY_EVENTS?month=$month&day=$day"
}