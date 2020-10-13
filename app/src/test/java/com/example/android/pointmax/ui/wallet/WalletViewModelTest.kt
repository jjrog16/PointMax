package com.example.android.pointmax.ui.wallet

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.pointmax.data.database.entities.Card
import com.getOrAwaitValue
import com.google.common.truth.Truth.assertThat

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WalletViewModelTest{
    
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    
    /**
     * Naming scheme as follows: subjectUnderTest_actionOrInput_resultState()
     */
    
    @Test
    fun displayCardDetails_cardSelected_cardIsSameAsTestCard(){
        // Test card
        val testCard = Card("Test Card")
        
        // Given a fresh viewModel
        val walletViewModel = WalletViewModel(ApplicationProvider.getApplicationContext())
        
        // When selecting a card
        walletViewModel.displayCardDetails(testCard)
        
        // Then the setNavigationToSelectedCard is triggered to navigate to the card given
        val value = walletViewModel.navigateToSelectedCard.getOrAwaitValue()
        
        assertThat(value).isEqualTo(testCard)
    }
    
}