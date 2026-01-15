package domain

import java.time.LocalDate
import java.util.UUID

interface CalendarRepository {

    suspend fun create(calendar: Calendar)

    suspend fun list(): List<Calendar>

    suspend fun getDays(calendarId: UUID): List<DayEntry>

    suspend fun toggleDay(calendarId: UUID, date: LocalDate)
}
