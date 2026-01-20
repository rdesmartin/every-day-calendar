package driven

import domain.Calendar
import ports.driven.CalendarRepository
import domain.DayEntry
import java.time.LocalDate
import java.util.UUID
import java.util.concurrent.ConcurrentHashMap

class StubCalendarRepository : CalendarRepository {

    private val calendars = ConcurrentHashMap<UUID, Calendar>()
    private val days = ConcurrentHashMap<Pair<UUID, LocalDate>, DayEntry>()

    override suspend fun create(calendar: Calendar) {
        calendars[calendar.id] = calendar
    }

    override suspend fun list(): List<Calendar> =
        calendars.values.toList()

    override suspend fun getDays(calendarId: UUID): List<DayEntry> =
        days.values.filter { it.calendarId == calendarId }

    override suspend fun toggleDay(calendarId: UUID, date: LocalDate): Boolean {
        val key = calendarId to date
        val day = days[key]

        if (day == null) {
            days[key] = DayEntry(calendarId, date, true)
            return true
        } else {
            days[key] = day.copy(completed = !day.completed)
            return !day.completed
        }
    }

    override suspend fun getDay(calendarId: UUID, date: LocalDate) {
        days.values.filter { it.calendarId == calendarId && it.date == date }
    }
}
