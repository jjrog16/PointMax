package com.example.android.pointmax

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pointmax.database.Card
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class CardAdapter : RecyclerView.Adapter<CardAdapter.CardViewHolder>() {
    
    private var cards = emptyList<Card>()
    inner class CardViewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        val cardItemView: TextView = itemView.textView
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
        val itemView = LayoutInflater.from(parent.context)
            .inflate(R.layout.recyclerview_item, parent, false)
        return CardViewHolder(itemView)
    }

    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val current = cards[position]
        holder.cardItemView.text = current.toString()
    }

    internal fun setCards(cards: List<Card>) {
        this.cards = cards
        notifyDataSetChanged()
    }

    override fun getItemCount() = cards.size
}