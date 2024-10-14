package ru.test.features.ui.favourite

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import dagger.hilt.android.AndroidEntryPoint
import ru.test.features.R
import ru.test.features.adapter.FavouriteVacancyAdapter
import ru.test.features.adapter.FavouriteVacancyOnInteractionListener
import ru.test.features.databinding.FragmentFavouritesBinding
import ru.test.features.ui.home.HomeViewModel
import java.util.UUID

@AndroidEntryPoint
class FavouritesFragment : Fragment() {
    private val viewModel: HomeViewModel by activityViewModels()

    private var _binding: FragmentFavouritesBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        _binding = FragmentFavouritesBinding.inflate(inflater, container, false)
        val root: View = binding.root

        // список Favourite Vacancy
        val adapterVacancy = FavouriteVacancyAdapter(object : FavouriteVacancyOnInteractionListener {
            override fun onFavoriteIcon(id: UUID) {
                // изменение поля isFavorite по id вакансии
                viewModel.changeFavouriteStateById(id)
            }
        })

        binding.favouriteVacancyRecyclerView.adapter = adapterVacancy
        viewModel.favouriteVacancies.observe(viewLifecycleOwner) { vacancies ->
            adapterVacancy.submitList(vacancies)

            binding.favouriteVacancyNumber.text = context?.resources?.getQuantityString(
                R.plurals.vacancies,
                vacancies.size,
                vacancies.size
            )
        }

        return root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}
