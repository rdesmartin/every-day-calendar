package driven.roomdb.entities

import androidx.room.Entity
import java.time.LocalDate
import java.util.UUID

@Entity(
    tableName = "day_entries",
    primaryKeys = ["calendarId", "date"]
)
data class DayEntryEntity(
    val calendarId: UUID,
    val date: LocalDate,
    val completed: Boolean
)