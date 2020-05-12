package com.example.android.pointmax

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pointmax.database.Card
import com.example.android.pointmax.databinding.RecyclerviewItemBinding
import com.example.android.pointmax.ui.wallet.WalletViewModel
import kotlinx.android.synthetic.main.recyclerview_item.view.*


class CardAdapter(val onClickListener: OnClickListener) :
    ListAdapter<Card, CardAdapter.CardViewHolder>(DiffCallback) {
    
    //private var cards =  emptyList<Card>()
    
    /**
     * The CardViewHolder constructor takes the binding variable from the associated
     * LayoutViewItem, which nicely gives it access to the full [Card] information.
     */
    class CardViewHolder (private var binding: RecyclerviewItemBinding) : RecyclerView.ViewHolder(binding.root) {
        fun bind (cards: Card){
            binding.creditCard = cards
            // This is important, because it forces the data binding to execute immediately,
            // which allows the RecyclerView to make the correct view size measurements
            binding.executePendingBindings()
        }
//        val cardItemView: TextView = itemView.card_name
    }
    
    /**
     * Allows the RecyclerView to determine which items have changed when the [List] of [Card]
     * has been updated.
     */
    companion object DiffCallback : DiffUtil.ItemCallback<Card>() {
        override fun areItemsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem === newItem
        }
        
        override fun areContentsTheSame(oldItem: Card, newItem: Card): Boolean {
            return oldItem.card == newItem.card
        }
    }
    
    /**
     * Create new [RecyclerView] item views (invoked by the layout manager)
     */
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CardViewHolder {
//        val itemView = LayoutInflater.from(context)
//            .inflate(R.layout.recyclerview_item, parent, false)
//        return CardViewHolder(itemView)
        return CardViewHolder(RecyclerviewItemBinding.inflate(LayoutInflater.from(parent.context)))
    }
    
    /**
     * Replaces the contents of a view (invoked by the layout manager)
     */
    override fun onBindViewHolder(holder: CardViewHolder, position: Int) {
        val current = getItem(position)
        holder.itemView.setOnClickListener {
            onClickListener.onClick(current)
        }
        holder.bind(current)
    }
    
    /**
     * Custom listener that handles clicks on [RecyclerView] items.  Passes the [Card]
     * associated with the current item to the [onClick] function.
     * @param clickListener lambda that will be called with the current [Card]
     */
    class OnClickListener(val clickListener: (cards : Card) -> Unit) {
        fun onClick(cards:Card) = clickListener(cards)
    }

    //override fun getItemCount() = cards.size
}