package ru.test.features.adapter.vacancy

import android.view.LayoutInflater
import android.view.ViewGroup
import com.hannesdorfmann.adapterdelegates4.AbsListItemAdapterDelegate
import ru.test.features.databinding.CardVacancyBinding

class VacancyAdapterDelegate(
    private val onInteractionListener: VacancyOnInteractionListener
) : AbsListItemAdapterDelegate<ListItem.VacancyItem, ListItem, VacancyViewHolder>() {

    override fun isForViewType(item: ListItem, items: MutableList<ListItem>, position: Int): Boolean {
        return item is ListItem.VacancyItem
    }

    override fun onCreateViewHolder(parent: ViewGroup): VacancyViewHolder {
        val binding = CardVacancyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return VacancyViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(item: ListItem.VacancyItem, holder: VacancyViewHolder, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            holder.bind(item.vacancy)
        } else {
            holder.bindPayload(payloads[0])
        }
    }
}