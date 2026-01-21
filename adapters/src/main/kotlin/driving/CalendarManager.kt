package driving
import domain.Calendar
import ports.driven.CalendarRepository
import ports.driving.ForManagingCalendars
import java.time.LocalDate
import java.util.UUID

class CalendarManager (
    private val repo: CalendarRepository
) : ForManagingCalendars {
    override suspend fun createCalendar(
        title: String,
        startDate: LocalDate
    ): UUID {
        val calendar = Calendar(
            id = UUID.randomUUID(),
            title = title,
            startDate = startDate
        )
        repo.create(calendar)
        return calendar.id
    }

    override suspend fun listCalendars(): List<Calendar> =
        repo.list()

    override suspend fun getById(id: UUID): Calendar? =
        repo.getById(id)
}