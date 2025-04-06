package com.bcit.mobileassignment.data

import io.ktor.client.HttpClient
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.client.request.header
import io.ktor.http.HttpHeaders
import io.ktor.serialization.gson.gson

const val API_KEY = "7Et6Ttuv8LbgSx6nex7uJw==6EwrXqnVyZLxsXgi"

val client = HttpClient {
    install(ContentNegotiation) {
        gson()
    }

    defaultRequest {
        header("X-Api-Key", API_KEY)
    }
}
