package ru.test.features.adapter.vacancy

import androidx.recyclerview.widget.RecyclerView
import ru.test.features.R
import ru.test.features.databinding.ButtonBinding

class ButtonViewHolder(
    private val binding: ButtonBinding,
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(buttonItem: ListItem.ButtonItem) {
        val context = binding.root.context
        val vacancyNumberText = context.resources.getQuantityString(
            R.plurals.more_vacancies,
            buttonItem.totalVacancies - 3,
            buttonItem.totalVacancies - 3
        )

        binding.buttonViewMore.text = vacancyNumberText
        binding.buttonViewMore.setOnClickListener {
            buttonItem.onClick()
        }
    }
}