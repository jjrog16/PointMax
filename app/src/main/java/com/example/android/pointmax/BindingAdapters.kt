package com.example.android.pointmax

import androidx.databinding.BindingAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.android.pointmax.database.Card


/**
 * When there is no Mars property data (data is null), hide the [RecyclerView], otherwise show it.
 */
@BindingAdapter("listData")
fun bindRecyclerView(recyclerView: RecyclerView, data: List<Card>?) {
    val adapter = recyclerView.adapter as CardAdapter
    adapter.submitList(data)
}