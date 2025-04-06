package com.bcit.mobileassignment.data

import com.google.gson.annotations.SerializedName

data class History(
    @SerializedName("data")
    val events: List<HistoryPiece> = emptyList()
)

data class HistoryPiece(
    val year: String,
    val month: Int,
    val day: Int,
    @SerializedName("event")
    val event: String
)
