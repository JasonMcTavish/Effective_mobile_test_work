package ru.test.features.adapter.vacancy

import androidx.recyclerview.widget.RecyclerView
import ru.test.domain.models.VacancyDomain
import ru.test.features.R
import ru.test.features.databinding.CardVacancyBinding
import ru.test.features.utils.DateFormatter
import java.util.UUID

interface VacancyOnInteractionListener {
    fun onRoot(id: UUID) {}
    fun onFavoriteIcon(id: UUID) {}
}

class VacancyViewHolder(
    private val binding: CardVacancyBinding,
    private val onInteractionListener: VacancyOnInteractionListener,
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
            } else {
                viewedBy.text = null
            }

            title.text = vacancy.title
            salary.text = vacancy.salary.full
            address.text = vacancy.address.town
            company.text = vacancy.company
            experience.text = vacancy.experience.previewText

            val dateFormatter = DateFormatter()
            val publishedDayAndMonth = dateFormatter.formatDate(vacancy.publishedDate)
            published.text = context.resources.getString(R.string.published, publishedDayAndMonth)

            root.setOnClickListener { onInteractionListener.onRoot(vacancy.id) }
            favouriteIcon.setOnClickListener { onInteractionListener.onFavoriteIcon(vacancy.id) }
            button.setOnClickListener { }
        }
    }

    fun bindPayload(payload: Any?) {
        if (payload is Boolean) {
            binding.favouriteIcon.isChecked = payload
        }
    }
}