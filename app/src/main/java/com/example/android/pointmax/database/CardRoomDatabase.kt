package com.example.android.pointmax.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch


// Annotates class to be a Room Database with a table (entity) of the Card class
@Database(entities = arrayOf(Card::class), version = 1, exportSchema = false)
public abstract class CardRoomDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CardRoomDatabase? = null
        
        fun getDatabase(
            context: Context,
            scope: CoroutineScope
        ): CardRoomDatabase {
            val tempInstance = INSTANCE
            if (tempInstance != null) {
                return tempInstance
            }
            synchronized(this) {
                val instance = Room.databaseBuilder(
                    context.applicationContext,
                    CardRoomDatabase::class.java,
                    "card_database"
                ).fallbackToDestructiveMigration()
                    .addCallback(CardDatabaseCallback(scope)).build()
                INSTANCE = instance
                return instance
            }
        }

        private class CardDatabaseCallback(
            private val scope: CoroutineScope
        ) : RoomDatabase.Callback() {

            override fun onOpen(db: SupportSQLiteDatabase) {
                super.onOpen(db)
                INSTANCE?.let { database ->
                    scope.launch {
                        populateDatabase(database.cardDao())
                    }
                }
            }

            suspend fun populateDatabase(cardDao: CardDao) {
                // Delete all content here.
                cardDao.deleteAll()

                // Add sample cards.
                var card = Card(cardName = "Petal Credit Card".toUpperCase())
                cardDao.insert(card)
                
                card = Card(cardName = "Generic Card".toUpperCase())
                cardDao.insert(card)
                
                card = Card(cardName = "American Express Gold".toUpperCase())
                cardDao.insert(card)
                
            }
        }
    }
}