package usecase

import domain.CalendarRepository
import domain.DayEntry
import java.time.LocalDate
import java.util.UUID

class ForTogglingDay(
    private val repo: CalendarRepository
) {
    suspend operator fun invoke(
        calendarId: UUID,
        date: LocalDate
    ) {
        repo.toggleDay(calendarId, date)
    }

    suspend fun getDays(id: UUID): List<DayEntry> {
        return repo.getDays(id)
    }
}
