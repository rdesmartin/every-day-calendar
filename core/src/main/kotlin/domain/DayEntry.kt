package domain

import java.time.LocalDate
import java.util.UUID

data class DayEntry(
    val calendarId: UUID,
    val date: LocalDate,
    val completed: Boolean
)
