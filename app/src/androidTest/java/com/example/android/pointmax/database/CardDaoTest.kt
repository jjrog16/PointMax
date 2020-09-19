package com.example.android.pointmax.database

import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.junit.runners.JUnit4

@RunWith(AndroidJUnit4::class)
class CardDaoTest {

    private lateinit var database: CardRoomDatabase
    private lateinit var dao: CardDao
    
    /**
     * Before each test, initialize a new Room database, store it in RAM memory, and initialize
     * the DAO
     */
    @Before
    fun setup(){
        database = Room.inMemoryDatabaseBuilder(
            ApplicationProvider.getApplicationContext(),
            CardRoomDatabase::class.java
        ).allowMainThreadQueries().build()
        
        dao = database.cardDao()
    }
    
    /**
     * Close the database after each test
     */
    @After
    fun teardown(){
        database.close()
    }
    
    /**
     * Naming scheme as follows: subjectUnderTest_actionOrInput_resultState()
     */
    
    
    @Test
    fun insert_insertACard_returnsCardInserted() = runBlockingTest {
        val testCard = Card("Amex Platinum", airlines = 5.0)
        dao.insert(testCard)
    }
}