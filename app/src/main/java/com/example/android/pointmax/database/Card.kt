package com.example.android.pointmax.database

import androidx.room.*

@Entity
data class Card(
    @PrimaryKey(autoGenerate = true)
    var cardId: Long = 0L,
    var cardName: String
)

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long = 0L,
    val cardCategoryId: Long,
    var type: String = "General",
    var earnRate: Double = 1.0,
    var protection: Int,
    var redeemValue: String
)

data class CreditCards(
    @Embedded val card: Card,
    @Relation(
        parentColumn = "cardId",
        entityColumn = "cardCategoryId"
    )
    val creditCards: List<Category>
)
