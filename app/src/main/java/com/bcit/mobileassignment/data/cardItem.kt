package com.bcit.mobileassignment.data

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf

data class CardItem(
    val id: String,
    val text: MutableState<String>,
    val isLocked: MutableState<Boolean>,
    val isChecked: MutableState<Boolean> = mutableStateOf(false)
)
