package com.example.android.pointmax.ui.add_custom_card

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.room.Room
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.pointmax.data.database.CardDao
import com.example.android.pointmax.data.database.CardRoomDatabase
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@RunWith(AndroidJUnit4::class)
class AddCustomCardFragmentTest{
    
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    
    private lateinit var database: CardRoomDatabase
    private lateinit var dao: CardDao
    private lateinit var AddCustomCardFragment: AddCustomCardFragment
    
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
    
    fun testAddCustomCardFragment(){
        val originalCard = "Original Card"
        
    }

}