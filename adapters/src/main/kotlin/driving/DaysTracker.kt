package driving

import ports.driven.CalendarRepository
import domain.DayEntry
import ports.driving.ForTrackingDays
import java.time.LocalDate
import java.util.UUID

class DaysTracker(
    private val repo: CalendarRepository
) : ForTrackingDays {
    override suspend fun getDays(id: UUID): List<DayEntry> {
        return repo.getDays(id)
    }

    override suspend fun toggleDay(
        calendarId: UUID, date: LocalDate
    ): Boolean {
        return repo.toggleDay(calendarId, date)
    }
}