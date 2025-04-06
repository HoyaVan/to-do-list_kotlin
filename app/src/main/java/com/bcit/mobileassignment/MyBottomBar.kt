package com.bcit.mobileassignment

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material3.BottomAppBar
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.snapshots.SnapshotStateList
import androidx.compose.ui.Modifier
import com.bcit.mobileassignment.data.CardItem

@Composable
fun MyBottomBar(cardList: SnapshotStateList<CardItem>) {
    BottomAppBar(
        containerColor = MaterialTheme.colorScheme.background
    ){
        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.Center
        ){
            IconButton(onClick = {
                val newCard = CardItem(
                    id = "Card #${cardList.size + 1}",
                    text = mutableStateOf(""),
                    isLocked = mutableStateOf(false)
                )
                cardList.add(newCard)
            }) {
                Icon(
                    imageVector = Icons.Default.Add,
                    contentDescription = "Add card"
                )
            }
        }
    }
}
