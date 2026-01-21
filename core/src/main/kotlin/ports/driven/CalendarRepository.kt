package ports.driven

import domain.Calendar
import domain.DayEntry
import java.time.LocalDate
import java.util.UUID

interface CalendarRepository {

    suspend fun create(calendar: Calendar)

    suspend fun list(): List<Calendar>

    suspend fun getById(calendarId: UUID): Calendar?

    suspend fun getDays(calendarId: UUID): List<DayEntry>

    suspend fun toggleDay(calendarId: UUID, date: LocalDate): Boolean

    suspend fun getDay(calendarId: UUID, date: LocalDate)
}