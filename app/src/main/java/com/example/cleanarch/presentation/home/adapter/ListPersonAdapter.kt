package com.example.cleanarch.presentation.home.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.example.cleanarch.databinding.ItemListPersonBinding
import com.example.cleanarch.presentation.home.adapter.ListPersonAdapter.MyHolder
import com.example.domain.model.Person

class ListPersonAdapter(
    private val listItems: List<Person>,
    private val onClickItem: (Person, Int) -> Unit
) : RecyclerView.Adapter<MyHolder>() {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyHolder {
        return MyHolder(
            ItemListPersonBinding
                .inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: MyHolder, position: Int) {
        holder.bind(listItems[position])
    }

    override fun getItemCount() = listItems.size

    inner class MyHolder(private val itemViewBinding: ItemListPersonBinding) :
        RecyclerView.ViewHolder(itemViewBinding.root) {
        fun bind(person: Person) {
            itemViewBinding.firstName.text = person.firstName
            itemViewBinding.secondName.text = person.secondName
            itemViewBinding.root.setOnClickListener {
                onClickItem.invoke(person, absoluteAdapterPosition)
            }
        }
    }
}
