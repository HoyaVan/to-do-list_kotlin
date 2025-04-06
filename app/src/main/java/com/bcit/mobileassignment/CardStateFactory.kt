import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.bcit.mobileassignment.data.HandledCardRepository

class CardStateFactory(
    private val repository: HandledCardRepository
) : ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        if (modelClass.isAssignableFrom(CardState::class.java)) {
            return CardState(repository) as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")
    }
}
