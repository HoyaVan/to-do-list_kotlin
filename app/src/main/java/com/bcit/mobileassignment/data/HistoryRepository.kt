package com.bcit.mobileassignment.data

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import io.ktor.client.HttpClient
import io.ktor.client.call.body
import io.ktor.client.request.get


class HistoryRepository(private val httpClient: HttpClient) {

    suspend fun getHistoryEvent(month: Int, day: Int): List<HistoryPiece> {
        val url = fieldsUrl(month, day)
        println("Requesting: $url")

        val response = httpClient.get(url)
        val json = response.body<String>()
        println("RAW JSON RESPONSE: $json")

        val type = object : TypeToken<List<HistoryPiece>>() {}.type
        return Gson().fromJson(json, type)
    }
}

