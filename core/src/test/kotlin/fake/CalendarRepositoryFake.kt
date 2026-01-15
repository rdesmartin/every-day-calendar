package fake

import domain.Calendar
import domain.CalendarRepository
import domain.DayEntry
import java.time.LocalDate
import java.util.UUID

class CalendarRepositoryFake: CalendarRepository {
    val calendars = mutableListOf<Calendar>()
    val days = mutableMapOf<Pair<UUID, LocalDate>, DayEntry>()

    override suspend fun create(calendar: Calendar) {
        calendars.add(calendar)
    }

    override suspend fun list(): List<Calendar> =
        calendars.toList()

    override suspend fun getDays(calendarId: UUID): List<DayEntry> =
        days.values.filter { it.calendarId == calendarId }

    override suspend fun toggleDay(calendarId: UUID, date: LocalDate) {
        val key = calendarId to date
        val existing = days[key]

        if (existing == null) {
            days[key] = DayEntry(calendarId, date, true)
        } else {
            days[key] = existing.copy(completed = !existing.completed)
        }
    }
}