package ru.test.features.ui.home

import androidx.lifecycle.LiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.asLiveData
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import ru.test.domain.models.OfferDomain
import ru.test.domain.models.VacancyDomain
import ru.test.domain.usercase.ChangeFavouriteUseCase
import ru.test.domain.usercase.GetAllOffersUseCase
import ru.test.domain.usercase.GetAllVacanciesUseCase
import ru.test.domain.usercase.GetDataFromApiUseCase
import ru.test.domain.usercase.GetFavouriteVacanciesUseCase
import java.util.UUID
import javax.inject.Inject

@HiltViewModel
class HomeViewModel @Inject constructor(
    private val getAllVacanciesUseCase: GetAllVacanciesUseCase,
    private val getFavouriteVacanciesUseCase: GetFavouriteVacanciesUseCase,
    private val getAllOffersUseCase: GetAllOffersUseCase,
    private val getDataFromApiUseCase: GetDataFromApiUseCase,
    private val changeFavouriteUseCase: ChangeFavouriteUseCase,
) : ViewModel() {
    // полный список рекомендаций (offer)
    val offers: LiveData<List<OfferDomain>> = getAllOffersUseCase().asLiveData()

    // полный список вакансий
    val vacancies: LiveData<List<VacancyDomain>> = getAllVacanciesUseCase().asLiveData()

    // список избранных вакансий
    val favouriteVacancies: LiveData<List<VacancyDomain>> = getFavouriteVacanciesUseCase().asLiveData()

    init {
        getData()
    }

    // получение данных по сети
    private fun getData() {
        viewModelScope.launch {
            try {
                getDataFromApiUseCase()
            } catch (_: Exception) {
            }
        }
    }

    // изменение поля isFavorite по id вакансии
    fun changeFavouriteStateById(id: UUID) {
        viewModelScope.launch {
            try {
                changeFavouriteUseCase(id)
            } catch (_: Exception) {
            }
        }
    }
}
