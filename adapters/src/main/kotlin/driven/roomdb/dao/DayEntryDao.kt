package driven.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import driven.roomdb.entities.DayEntryEntity
import java.time.LocalDate
import java.util.UUID

@Dao
interface DayEntryDao {

    @Query("""
        SELECT * FROM day_entries 
        WHERE calendarId = :calendarId
    """)
    suspend fun getForCalendar(calendarId: UUID): List<DayEntryEntity>

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun upsert(entry: DayEntryEntity)

    @Query("""
        SELECT * FROM day_entries
        WHERE calendarId = :calendarId AND DATE = :date
    """)
    suspend fun getDayEntry(calendarId: UUID, date: LocalDate): DayEntryEntity?
}
