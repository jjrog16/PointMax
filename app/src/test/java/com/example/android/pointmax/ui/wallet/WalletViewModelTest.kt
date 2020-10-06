package com.example.android.pointmax.ui.wallet

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.example.android.pointmax.database.Card
import com.getOrAwaitValue
import com.google.common.truth.Truth.assertThat

import org.junit.Rule
import org.junit.Test
import org.junit.runner.RunWith

@RunWith(AndroidJUnit4::class)
class WalletViewModelTest{
    
    @get:Rule
    var instantTaskExecutorRule = InstantTaskExecutorRule()
    
    @Test
    fun displayCardDetails_setNavigateToSelectedCard(){
        // Test card
        val testCard = Card("Test Card")
        
        // Given a fresh viewModel
        val walletViewModel = WalletViewModel(ApplicationProvider.getApplicationContext())
        
        // When selecting a card
        walletViewModel.displayCardDetails(testCard)
        
        // Then the setNavigationToSelectedCard is triggered
        val value = walletViewModel.navigateToSelectedCard.getOrAwaitValue()
        
        assertThat(value).isEqualTo(testCard)
        
    }
    
}