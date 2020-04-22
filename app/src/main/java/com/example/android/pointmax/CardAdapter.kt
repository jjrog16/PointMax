package com.example.android.pointmax

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.lifecycle.LiveData
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.database.CardRepository
import timber.log.Timber

class CardAdapter internal constructor(
    context: Context
) : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    
    private val inflater: LayoutInflater = LayoutInflater.from(context)
    private var cards = emptyList<Card>() // Cached copy of words
    
    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardItemView: TextView = itemView.findViewById(R.id.textView)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context).inflate(R.layout.recyclerview_item, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val current = cards[position]
        holder.cardItemView.text = current.toString()
        Timber.i("Current card: $current")
    }

    internal fun setWords(cards: List<Card>) {
        this.cards = cards
        notifyDataSetChanged()
    }

    override fun getItemCount() = cards.size
}