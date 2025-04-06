import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.bcit.mobileassignment.data.CardItem
import com.bcit.mobileassignment.data.HandledCard
import com.bcit.mobileassignment.data.HandledCardRepository
import com.bcit.mobileassignment.toHandledCard
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

class CardState(private val repository: HandledCardRepository) : ViewModel() {

    var cards = mutableStateListOf<CardItem>()
        private set

    var achievedCards = mutableStateListOf<HandledCard>()
        private set

    init {
        loadAchievements()
    }

    private fun loadAchievements() {
        viewModelScope.launch(Dispatchers.IO) {
            val all = repository.getAllCards()
            achievedCards.addAll(all)
        }
    }

    fun add(card: CardItem) {
        viewModelScope.launch(Dispatchers.IO) {
            val handled = card.toHandledCard()
            repository.insertCard(handled)
            achievedCards.add(handled)
            cards.remove(card)
        }
    }

    fun clearCards() {
        viewModelScope.launch(Dispatchers.IO) {
            val all = repository.getAllCards()
            cards.clear()
            cards.addAll(all.map {
                CardItem(
                    id = it.id.toString(),
                    text = mutableStateOf(it.text),
                    isLocked = mutableStateOf(true)
                )
            })
            cards.clear()
        }
    }

    fun refresh() {
        viewModelScope.launch(Dispatchers.IO) {
            val all = repository.getAllCards()
            cards.clear()
            cards.addAll(all.map {
                CardItem(
                    id = it.id.toString(),
                    text = mutableStateOf(it.text),
                    isLocked = mutableStateOf(true)
                )
            })
            achievedCards.clear()
            achievedCards.addAll(all)
        }
    }

    fun clearAchievements() {
        viewModelScope.launch(Dispatchers.IO) {
            repository.clearAllCards()
            achievedCards.clear()
        }
    }

}
