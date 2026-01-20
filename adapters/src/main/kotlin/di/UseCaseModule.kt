package di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ViewModelComponent
import driving.CalendarManager
import driving.DaysTracker
import ports.driven.CalendarRepository

@Module
@InstallIn(ViewModelComponent::class)
object UseCaseModule {

    @Provides
    fun provideCalendarManager(repo: CalendarRepository) =
        CalendarManager(repo)

    @Provides
    fun provideDaysTracker(repo: CalendarRepository) =
        DaysTracker(repo)
}