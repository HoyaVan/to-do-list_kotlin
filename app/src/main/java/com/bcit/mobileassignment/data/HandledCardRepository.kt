package com.bcit.mobileassignment.data

class HandledCardRepository(private val dao: HandledCardDao) {

    suspend fun insertCard(card: HandledCard) {
        dao.insert(card)
    }

    suspend fun getAllCards(): List<HandledCard> {
        return dao.getAll()
    }

    suspend fun clearAllCards() {
        dao.clearAll()
    }
}

