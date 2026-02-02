package driven.roomdb.dao

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import driven.roomdb.entities.CalendarEntity
import kotlinx.coroutines.flow.Flow
import java.util.UUID

@Dao
interface CalendarDao {

    @Query("SELECT * FROM calendars")
    suspend fun getAll(): List<CalendarEntity>

    @Query("SELECT * FROM calendars WHERE id = :id")
    suspend fun getById(id: UUID): CalendarEntity?

    @Insert
    suspend fun insert(calendar: CalendarEntity)
}
