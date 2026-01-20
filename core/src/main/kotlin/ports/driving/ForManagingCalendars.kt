package ports.driving

import domain.Calendar
import java.time.LocalDate
import java.util.UUID

interface ForManagingCalendars {
    suspend fun createCalendar(
        title: String,
        startDate: LocalDate= LocalDate.now()
    ): UUID

    suspend fun listCalendars(): List<Calendar>
}