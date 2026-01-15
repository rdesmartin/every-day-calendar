package usecase
import domain.Calendar
import domain.CalendarRepository
import java.time.LocalDate
import java.util.UUID

class ForManagingCalendars (
    private val repo: CalendarRepository
) {
    suspend fun createCalendar(
        title: String,
        startDate: LocalDate = LocalDate.now()
    ) {
        val calendar = Calendar(
            id = UUID.randomUUID(),
            title = title,
            startDate = startDate
        )
        repo.create(calendar)
    }

    suspend fun listCalendars(): List<Calendar> =
        repo.list()
}