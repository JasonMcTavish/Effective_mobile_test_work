package ru.test.features.ui.home

import android.content.Intent
import android.net.Uri
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.test.domain.models.VacancyDomain
import ru.test.features.R
import ru.test.features.adapter.OfferAdapter
import ru.test.features.adapter.OfferOnInteractionListener
import ru.test.features.adapter.vacancy.ListItem
import ru.test.features.adapter.vacancy.VacancyOnInteractionListener
import ru.test.features.adapter.vacancy.VacancyWithButtonAdapter
import ru.test.features.databinding.FragmentHomeBinding
import java.util.UUID

@AndroidEntryPoint
class HomeFragment : Fragment() {
    private val viewModel: HomeViewModel by activityViewModels()
    private var _binding: FragmentHomeBinding? = null
    private var isExpanded = false
    private var isFavoriteChanged = false
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?,
    ): View {
        _binding = FragmentHomeBinding.inflate(inflater, container, false)
        val root: View = binding.root

        changeVisibility(true)

        // список Offer
        val adapterOffer = OfferAdapter(object : OfferOnInteractionListener {
            override fun onRoot(link: String) {
                val intent = Intent(Intent.ACTION_VIEW, Uri.parse(link))
                startActivity(intent)
            }
        })
        binding.offerRecyclerView.adapter = adapterOffer
        viewModel.offers.observe(viewLifecycleOwner) { offers ->
            adapterOffer.submitList(offers)
        }

        // список Vacancy
        val adapterVacancy = VacancyWithButtonAdapter(object : VacancyOnInteractionListener {
            override fun onRoot(id: UUID) {
                // TODO
            }

            override fun onFavoriteIcon(id: UUID) {
                isFavoriteChanged = true
                viewModel.changeFavouriteStateById(id)
            }
        })
        binding.vacancyRecyclerView.adapter = adapterVacancy
        viewModel.vacancies.observe(viewLifecycleOwner) { vacancies ->
            binding.vacancyNumber.text = context?.resources?.getQuantityString(
                R.plurals.vacancies,
                vacancies.size,
                vacancies.size
            )
            if (vacancies.isNotEmpty()) {
                binding.backIcon.setOnClickListener {
                    changeVisibility(true)
                    setInitialListWithButton(adapterVacancy, vacancies)
                }

                if (!isExpanded) {
                    changeVisibility(true)
                    setInitialListWithButton(adapterVacancy, vacancies)
                } else {
                    // Показываются все вакансии
                    adapterVacancy.submitList(vacancies.map { ListItem.VacancyItem(it) })
                }
            }
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    private fun changeVisibility(isInitialState: Boolean) {
        binding.initialGroup.isVisible = isInitialState
        binding.searchIcon.isVisible = isInitialState

        binding.expandedGroup.isVisible = !isInitialState
        binding.backIcon.isVisible = !isInitialState

        isExpanded = !isInitialState
    }

    private fun setInitialListWithButton(
        adapterVacancy: VacancyWithButtonAdapter,
        vacancies: List<VacancyDomain>,
    ) {
        val initialList = vacancies.take(3).map { ListItem.VacancyItem(it) } +
            listOf(
                ListItem.ButtonItem(vacancies.size) {
                    isExpanded = true

                    changeVisibility(false)
                    adapterVacancy.submitList(vacancies.map { ListItem.VacancyItem(it) })
                }
            )
        adapterVacancy.submitList(initialList)
    }
}
