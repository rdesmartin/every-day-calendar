package domain

import java.time.LocalDate
import java.util.UUID

data class Calendar(
    val id: UUID,
    val title: String,
    val startDate: LocalDate,
    val durationDays: Int = 365
)
