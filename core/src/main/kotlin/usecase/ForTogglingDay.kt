package usecase

import domain.CalendarRepository
import java.time.LocalDate
import java.util.UUID

class ForMarkingDayCompleted(
    private val repo: CalendarRepository
) {
    suspend operator fun invoke(
        calendarId: UUID,
        date: LocalDate
    ) {
        repo.toggleDay(calendarId, date)
    }
}
