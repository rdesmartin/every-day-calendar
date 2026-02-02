package driven.roomdb

import androidx.room.Database
import androidx.room.RoomDatabase
import androidx.room.TypeConverters
import driven.roomdb.dao.CalendarDao
import driven.roomdb.dao.DayEntryDao
import driven.roomdb.entities.CalendarEntity
import driven.roomdb.entities.DayEntryEntity

@Database(
    entities = [
        CalendarEntity::class,
        DayEntryEntity::class
    ],
    version = 1
)
@TypeConverters(Converters::class)
abstract class AppDatabase : RoomDatabase() {
    abstract fun calendarDao(): CalendarDao
    abstract fun dayEntryDao(): DayEntryDao
}
