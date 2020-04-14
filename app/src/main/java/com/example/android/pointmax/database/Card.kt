package com.example.android.pointmax.database

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "card_table")
data class Card(@PrimaryKey @ColumnInfo(name = "card") val card: String)

