package com.bcit.mobileassignment

import CardState
import androidx.compose.foundation.clickable
import androidx.compose.foundation.gestures.detectDragGestures
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.input.pointer.pointerInput
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.IntOffset
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import com.bcit.mobileassignment.data.CardItem
import com.bcit.mobileassignment.data.HandledCard
import kotlin.math.roundToInt

fun CardItem.toHandledCard(): HandledCard {
    return HandledCard(
        text = this.text.value,
        isLocked = this.isLocked.value
    )
}

@Composable
fun Home(
    navController: NavController,
    viewModel: CardState
) {
    val cardList = viewModel.cards

    LazyColumn {
        items(cardList, key = { it.id }) { card ->
            SwipeableCardWrapper(
                card = card,
                onSwipeRight = {
                    viewModel.add(card)
                },
                onSwipeLeft = {
                    viewModel.cards.remove(card)
                },
                content = {
                    MyCard(card = card, onDelete = { viewModel.cards.remove(it) })
                }
            )

        }
    }
}

@Composable
fun MyCard(
    card: CardItem,
    onDelete: (CardItem) -> Unit
) {

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .clickable {
                if (card.isLocked.value) {
                    card.isLocked.value = false // Unlock on tap
                }
            }
    ) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            if (card.isLocked.value) {
                Text(
                    text = card.text.value,
                    modifier = Modifier
                        .weight(1f)
                        .padding(start = 8.dp),
                    textDecoration = if (card.isChecked.value) {
                        TextDecoration.LineThrough
                    } else {
                        TextDecoration.None
                    }
                )
            } else {
                TextField(
                    value = card.text.value,
                    onValueChange = { card.text.value = it },
                    modifier = Modifier.weight(1f),
                    placeholder = { Text("Enter task...") },
                    singleLine = true
                )

                IconButton(onClick = {
                    if (card.text.value.isBlank()) {
                        onDelete(card) // delete if empty
                    } else {
                        card.isLocked.value = true // lock if not empty
                    }
                }) {
                    Icon(
                        imageVector = Icons.Default.Lock,
                        contentDescription = "Lock card"
                    )
                }
            }
        }
    }
}

@Composable
fun SwipeableCardWrapper(
    card: CardItem,
    onSwipeRight: () -> Unit,
    onSwipeLeft: () -> Unit,
    content: @Composable () -> Unit
) {
    var offsetX by remember { mutableStateOf(0f) }

    Box(
        modifier = Modifier
            .fillMaxWidth()
            .offset { IntOffset(offsetX.roundToInt(), 0) }
            .pointerInput(Unit) {
                detectDragGestures(
                    onDrag = { _, dragAmount -> offsetX += dragAmount.x },
                    onDragEnd = {
                        when {
                            offsetX > 300 -> onSwipeRight()
                            offsetX < -300 -> onSwipeLeft()
                        }
                        offsetX = 0f
                    }
                )
            }
    ) {
        content()
    }
}
