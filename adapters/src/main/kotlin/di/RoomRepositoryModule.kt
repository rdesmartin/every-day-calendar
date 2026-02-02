package di

import dagger.Binds
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import driven.roomdb.RoomCalendarRepository
import ports.driven.CalendarRepository

@Module
@InstallIn(SingletonComponent::class)
abstract class RoomRepositoryModule { // Provides RoomCalendarRepository to Viewmodels in the app

    @Binds
    abstract fun bindCalendarRepo(
        impl: RoomCalendarRepository
    ): CalendarRepository
}