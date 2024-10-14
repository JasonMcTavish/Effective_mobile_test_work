package ru.test.features.adapter.vacancy

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.test.features.databinding.ButtonBinding

class ButtonAdapterDelegate(
) : AbsListItemAdapterDelegate<ListItem.ButtonItem, ListItem, ButtonViewHolder>() {
    override fun isForViewType(
        item: ListItem,
        items: MutableList<ListItem>,
        position: Int
    ): Boolean {
        return item is ListItem.ButtonItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): ButtonViewHolder {
        val binding = ButtonBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return ButtonViewHolder(binding)
    }

    override fun onBindViewHolder(
        item: ListItem.ButtonItem, holder: ButtonViewHolder, payloads: MutableList<Any>
    ) {
        holder.bind(item)
    }
}