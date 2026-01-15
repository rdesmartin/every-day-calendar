package usecase
import domain.Calendar
import domain.CalendarRepository
import java.time.LocalDate
import java.util.UUID

class ForCreatingCalendar (
    private val repo: CalendarRepository
) {
    suspend operator fun invoke(
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
}