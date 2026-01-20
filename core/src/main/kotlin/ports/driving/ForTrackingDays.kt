package ports.driving

import domain.DayEntry
import java.time.LocalDate
import java.util.UUID

interface ForTrackingDays {

    suspend fun getDays(id: UUID): List<DayEntry>

    suspend fun toggleDay(calendarId: UUID, date: LocalDate): Boolean
}