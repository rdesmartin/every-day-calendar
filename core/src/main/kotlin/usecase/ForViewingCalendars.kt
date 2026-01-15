package usecase

import domain.Calendar
import domain.CalendarRepository

class ForViewingCalendars(
    private val repo: CalendarRepository
) {
    suspend operator fun invoke(): List<Calendar> =
        repo.list()
}
