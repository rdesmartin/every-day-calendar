package driven.roomdb.entities

import androidx.room.Entity
import androidx.room.PrimaryKey
import java.time.LocalDate
import java.util.UUID

@Entity(tableName = "calendars")
data class CalendarEntity(
    @PrimaryKey val id: UUID,
    val title: String,
    val startDate: LocalDate
)
