package ru.test.features.adapter.vacancy

import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.hannesdorfmann.adapterdelegates4.AdapterDelegatesManager
import ru.test.domain.models.VacancyDomain

sealed class ListItem {
    data class VacancyItem(val vacancy: VacancyDomain) : ListItem()
    data class ButtonItem(
        val totalVacancies: Int,
        val onClick: () -> Unit) : ListItem()
}

class VacancyWithButtonAdapter(
    private val onInteractionListener: VacancyOnInteractionListener,
) : ListAdapter<ListItem, RecyclerView.ViewHolder>(VacancyDiffCallback()) {

    private val delegatesManager = AdapterDelegatesManager<List<ListItem>>()

    init {
        delegatesManager.addDelegate(VacancyAdapterDelegate(onInteractionListener))
        delegatesManager.addDelegate(ButtonAdapterDelegate())
    }

    override fun getItemViewType(position: Int): Int {
        return delegatesManager.getItemViewType(currentList, position)
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): RecyclerView.ViewHolder {
        return delegatesManager.onCreateViewHolder(parent, viewType)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        delegatesManager.onBindViewHolder(currentList, position, holder)
    }

    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int, payloads: MutableList<Any>) {
        if (payloads.isEmpty()) {
            super.onBindViewHolder(holder, position, payloads)
        } else {
            delegatesManager.onBindViewHolder(currentList, position, holder, payloads)
        }
    }
}

class VacancyDiffCallback : DiffUtil.ItemCallback<ListItem>() {
    override fun areItemsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return when {
            oldItem is ListItem.VacancyItem && newItem is ListItem.VacancyItem -> {
                oldItem.vacancy.id == newItem.vacancy.id
            }
            oldItem is ListItem.ButtonItem && newItem is ListItem.ButtonItem -> true
            else -> false
        }
    }

    override fun areContentsTheSame(oldItem: ListItem, newItem: ListItem): Boolean {
        return when {
            oldItem is ListItem.VacancyItem && newItem is ListItem.VacancyItem -> {
                oldItem == newItem
            }
            oldItem is ListItem.ButtonItem && newItem is ListItem.ButtonItem -> {
                oldItem.totalVacancies == newItem.totalVacancies
            }
            else -> false
        }
    }

    override fun getChangePayload(oldItem: ListItem, newItem: ListItem): Any? {
        return when {
            oldItem is ListItem.VacancyItem && newItem is ListItem.VacancyItem -> {
                if (oldItem.vacancy.isFavorite != newItem.vacancy.isFavorite) {
                    newItem.vacancy.isFavorite
                } else {
                    null
                }
            }
            else -> null
        }
    }
}
