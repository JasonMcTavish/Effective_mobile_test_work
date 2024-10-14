package ru.test.features.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.test.domain.models.VacancyDomain
import ru.test.features.R
import ru.test.features.databinding.CardVacancyBinding
import ru.test.features.utils.DateFormatter
import java.util.UUID

interface FavouriteVacancyOnInteractionListener {
    fun onFavoriteIcon(id: UUID) {}
}

class FavouriteVacancyAdapter(
    private val onInteractionListener: FavouriteVacancyOnInteractionListener
) : ListAdapter<VacancyDomain, FavouriteVacancyViewHolder>(FavouriteVacancyDiffCallback()) {

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavouriteVacancyViewHolder {
        val binding = CardVacancyBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FavouriteVacancyViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: FavouriteVacancyViewHolder, position: Int) {
        val vacancy = getItem(position)
        holder.bind(vacancy)
    }
}

class FavouriteVacancyViewHolder(
    private val binding: CardVacancyBinding,
    private val onInteractionListener: FavouriteVacancyOnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(vacancy: VacancyDomain) {
        binding.apply {
            favouriteIcon.isChecked = vacancy.isFavorite

            val context = root.context

            if (vacancy.lookingNumber != null) {
                val lookingNumber = vacancy.lookingNumber
                val peopleWatchingText = context.resources.getQuantityString(
                    R.plurals.people_watching,
                    lookingNumber ?: 0,
                    lookingNumber
                )

                viewedBy.text = peopleWatchingText
            }

            title.text = vacancy.title
            salary.text = vacancy.salary.full
            address.text = vacancy.address.town
            company.text = vacancy.company
            experience.text = vacancy.experience.previewText

            val dateFormatter = DateFormatter()
            val publishedDayAndMonth = dateFormatter.formatDate(vacancy.publishedDate)
            published.text = context.resources.getString(R.string.published, publishedDayAndMonth)

            favouriteIcon.setOnClickListener { onInteractionListener.onFavoriteIcon(vacancy.id) }
            button.setOnClickListener { }
        }
    }
}

class FavouriteVacancyDiffCallback : DiffUtil.ItemCallback<VacancyDomain>() {
    override fun areContentsTheSame(oldItem: VacancyDomain, newItem: VacancyDomain): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: VacancyDomain, newItem: VacancyDomain): Boolean {
        return oldItem.id == newItem.id
    }
}
