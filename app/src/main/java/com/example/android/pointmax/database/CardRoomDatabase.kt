package com.example.android.pointmax.database

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import androidx.room.migration.Migration
import androidx.sqlite.db.SupportSQLiteDatabase
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.launch
import timber.log.Timber


// Annotates class to be a Room Database with a table (entity) of the Word class
@Database(entities = arrayOf(Card::class), version = 1, exportSchema = false)
public abstract class CardRoomDatabase : RoomDatabase() {

    abstract fun cardDao(): CardDao

    companion object {
        // Singleton prevents multiple instances of database opening at the
        // same time.
        @Volatile
        private var INSTANCE: CardRoomDatabase? = null
    
//        // Migration from 1 to 2, Room 2.2.5
//        val MIGRATION_1_2 = object : Migration(1, 2) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("DROP TABLE card_table ")
//                database.execSQL("""
//                    CREATE TABLE Card (
//                        cardId INTEGER PRIMARY KEY NOT NULL,
//                        cardName TEXT NOT NULL
//                    )
//                    """.trimIndent())
//                database.execSQL("""
//                    CREATE TABLE Category(
//                        categoryId INTEGER PRIMARY KEY NOT NULL,
//                        cardCategoryId INTEGER,
//                        type TEXT,
//                        earnRate DOUBLE,
//                        protection INTEGER,
//                        redeemValue TEXT
//                    )
//                """.trimIndent())
//            }
//        }
//
//        val MIGRATION_2_3 = object : Migration(2,3) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("DROP TABLE Card ")
//                database.execSQL("""
//                    CREATE TABLE Card (
//                        cardId INTEGER 0 PRIMARY KEY NOT NULL,
//                        cardName TEXT NOT NULL
//                    )
//                    """.trimIndent())
//                database.execSQL("UPDATE Card SET cardId = 0 ")
//            }
//        }
//
//        val MIGRATION_3_4 = object : Migration(3,4) {
//            override fun migrate(database: SupportSQLiteDatabase) {
//                database.execSQL("DROP TABLE Card ")
//                database.execSQL("DROP TABLE Category ")
//                database.execSQL(
//                    """
//                    CREATE TABLE Card (
//                        cardId INTEGER PRIMARY KEY NOT NULL,
//                        cardName TEXT NOT NULL
//                    )
//                    """.trimIndent()
//                )
//                database.execSQL(
//                    """
//                    CREATE TABLE Category(
//                        categoryId INTEGER PRIMARY KEY NOT NULL,
//                        cardCategoryId INTEGER,
//                        type TEXT,
//                        earnRate DOUBLE,
//                        protection INTEGER,
//                        redeemValue TEXT
//                    )
//                """.trimIndent()
//                )
//            }
//        }
        
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
                ).addCallback(CardDatabaseCallback(scope)).build()
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

                // Add sample words.
                var card = Card(cardName = "Petal Credit Card")
                cardDao.insert(card)
                card = Card(cardName = "Discover IT")
                cardDao.insert(card)
            }
        }
    }
}