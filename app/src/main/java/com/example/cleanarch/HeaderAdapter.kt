package com.example.cleanarch

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarch.databinding.ItemListHeaderBinding
import com.example.cleanarch.databinding.ItemListPersonBinding
import com.example.domain.model.Person

class HeaderAdapter(
    private val title: String
) : RecyclerView.Adapter<HeaderAdapter.MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(ItemListHeaderBinding.inflate(LayoutInflater.from(parent.context), parent, false))
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(title)
    }

    override fun getItemCount() = COUNT_HEADER

    inner class MyHolder(private val itemViewBinding: ItemListHeaderBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(title: String) {
            itemViewBinding.titleHeader.text = title
        }
    }

    companion object{
        private const val COUNT_HEADER = 1
    }
}
