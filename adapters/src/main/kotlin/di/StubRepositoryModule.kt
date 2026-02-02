package di

import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import ports.driven.CalendarRepository
import driven.StubCalendarRepository
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object StubRepositoryModule {
    // Only one implementation of CalendarRepository can have the @Provides or @Inject annotation,
    // otherwise Dagger does not know which impl to inject.
    //    @Provides
    @Singleton
    fun provideCalendarRepository(): CalendarRepository =
        StubCalendarRepository()
}
