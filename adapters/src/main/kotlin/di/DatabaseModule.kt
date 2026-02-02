package di

import android.content.Context
import androidx.room.Room
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent
import driven.roomdb.AppDatabase
import javax.inject.Singleton

@Module
@InstallIn(SingletonComponent::class)
object DatabaseModule { // Provides Database and DAOs to repository

    @Provides
    @Singleton
    fun provideDatabase(
        @ApplicationContext context: Context
    ): AppDatabase =
        Room.databaseBuilder(
            context,
            AppDatabase::class.java,
            "calendar.db"
        ).build()

    @Provides fun provideCalendarDao(db: AppDatabase) = db.calendarDao()
    @Provides fun provideDayEntryDao(db: AppDatabase) = db.dayEntryDao()
}
