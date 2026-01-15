package usecase

import domain.CalendarRepository
import domain.DayEntry
import java.time.LocalDate
import java.util.UUID

class ForTrackingDays(
    private val repo: CalendarRepository
) {
    suspend fun getDays(id: UUID): List<DayEntry> {
        return repo.getDays(id)
    }

    suspend fun toggleDay(
        calendarId: UUID, date: LocalDate
    ) {
        repo.toggleDay(calendarId, date)
    }
}