package com.example.everydaycalendar.di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import domain.CalendarRepository
import usecase.ForManagingCalendars
import usecase.ForTrackingDays

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideForManagingCalendars(repo: CalendarRepository) =
        ForManagingCalendars(repo)

    @Provides
    fun provideForTrackingDays(repo: CalendarRepository) =
        ForTrackingDays(repo)
}
