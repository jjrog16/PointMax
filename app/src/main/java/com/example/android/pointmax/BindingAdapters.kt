package com.example.android.pointmax

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pointmax.data.database.entities.Card


/**
 * When there are no Cards (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Card>?) {
    val adapter = recyclerView.adapter as CardAdapter
    adapter.submitList(data)
}