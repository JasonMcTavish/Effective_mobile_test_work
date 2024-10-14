package ru.test.features.adapter

import android.graphics.drawable.GradientDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.core.content.ContextCompat
import androidx.core.view.isVisible
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.test.domain.models.OfferDomain
import ru.test.features.R
import ru.test.features.databinding.CardOfferBinding

interface OfferOnInteractionListener {
    fun onRoot(link: String) {}
}

class OfferAdapter(
    private val onInteractionListener: OfferOnInteractionListener,
) : ListAdapter<OfferDomain, OfferViewHolder>(OfferDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): OfferViewHolder {
        val binding =
            CardOfferBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return OfferViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: OfferViewHolder, position: Int) {
        val order = getItem(position)
        holder.bind(order)

        if (position == 0) {
            val params = holder.itemView.layoutParams as ViewGroup.MarginLayoutParams
            params.marginStart = 0
            holder.itemView.layoutParams = params
        }
    }

}

class OfferViewHolder(
    private val binding: CardOfferBinding,
    private val onInteractionListener: OfferOnInteractionListener
) : RecyclerView.ViewHolder(binding.root) {
    fun bind(offer: OfferDomain) {
        binding.apply {
            // установка цвета и иконки в зависимости от id
            when (offer.id) {
                "near_vacancies" -> {
                    icon.visibility = View.VISIBLE
                    setCircleBackgroundColor(icon, R.color.DarkBlue)
                    // TODO Отсутствует иконка в Figma
                    //  icon.setImageResource(R.drawable.?)
                }

                "level_up_resume" -> {
                    icon.visibility = View.VISIBLE
                    setCircleBackgroundColor(icon, R.color.DarkGreen)
                    icon.setImageResource(R.drawable.level_up)
                }

                "temporary_job" -> {
                    icon.visibility = View.VISIBLE
                    setCircleBackgroundColor(icon, R.color.DarkGreen)
                    icon.setImageResource(R.drawable.temporary)
                }

                else -> {
                    icon.visibility = View.INVISIBLE
                }
            }

            // установка количества строк заголовка и button.text в зависимости от наличия поля button
            title.text = offer.title.trim()
            if (offer.button != null) {
                title.setLines(2)
                button.isVisible = true
                button.text = offer.button?.text
            } else {
                title.setLines(3)
                button.isVisible = false
            }

            root.setOnClickListener { onInteractionListener.onRoot(offer.link) }
        }
    }
}

class OfferDiffCallback : DiffUtil.ItemCallback<OfferDomain>() {
    override fun areContentsTheSame(oldItem: OfferDomain, newItem: OfferDomain): Boolean {
        return oldItem == newItem
    }

    override fun areItemsTheSame(oldItem: OfferDomain, newItem: OfferDomain): Boolean {
        return oldItem.title == newItem.title
    }
}

// изменение цвета иконки
fun setCircleBackgroundColor(imageView: ImageView, colorResId: Int) {
    val drawable = imageView.background as GradientDrawable
    drawable.setColor(ContextCompat.getColor(imageView.context, colorResId))
    imageView.background = drawable
}