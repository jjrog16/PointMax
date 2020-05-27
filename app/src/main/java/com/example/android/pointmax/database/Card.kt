package com.example.android.pointmax.database

import androidx.room.*

@Entity
data class Card(
    @PrimaryKey
    var cardName: String
)

@Entity
data class Category(
    @PrimaryKey(autoGenerate = true)
    val categoryId: Long = 0L,
    val cardCategoryId: String,
    var type: String = "General",
    var earnRate: Double = 1.0,
    var protection: Int = 0,
    var redeemValue: String = "cash"
)

data class CreditCards(
    @Embedded val card: Card,
    @Relation(
        parentColumn = "cardName",
        entityColumn = "cardCategoryId"
    )
    val categories: List<Category>
)
