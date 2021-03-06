package com.example.android.pointmax.data.database.entities

import androidx.room.*

@Entity
data class Card(
    @PrimaryKey
    var cardName: String,
    var general: Double = 1.0,
    var groceries: Double = general,
    var restaurants: Double = general,
    var gas: Double = general,
    var airlines: Double = general,
    var travel: Double = general
)

