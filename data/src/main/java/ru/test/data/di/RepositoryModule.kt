package ru.test.data.di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ru.test.data.repository.ChangeVacancyRepositoryImpl
import ru.test.data.repository.DataRepositoryImpl
import ru.test.data.repository.FavouriteRepositoryImpl
import ru.test.domain.repository.ChangeVacancyRepository
import ru.test.domain.repository.DataRepository
import ru.test.domain.repository.FavouriteRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RepositoryModule {

    @Binds
    abstract fun bindDataRepository(impl: DataRepositoryImpl): DataRepository

    @Binds
    abstract fun bindChangeVacancyRepository(impl: ChangeVacancyRepositoryImpl): ChangeVacancyRepository

    @Binds
    abstract fun bindFavouriteRepository(impl: FavouriteRepositoryImpl): FavouriteRepository
}