package com.example.everydaycalendar.ui.components

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import domain.Calendar
import domain.DayEntry
import java.time.LocalDate
import java.util.UUID

@Composable
fun CalendarGrid(
    calendar: Calendar,
    days: List<DayEntry>,
    onDayClick: (LocalDate) -> Unit,
    today: LocalDate = LocalDate.now()
) {
    val completedDays = days
        .filter { it.completed }
        .map { it.date }
        .toSet()

    // create list of all days from start of calendar to end of year
    val allDates = (0 until calendar.durationDays).map {
        calendar.startDate.plusDays(it.toLong())
    }

    val datesByMonth = allDates.groupBy {
        it.year to it.monthValue
    }

    Row(
        horizontalArrangement = Arrangement.spacedBy(12.dp)
    ) {
        datesByMonth.forEach { (yearMonth, dates) ->
            val (year, month) = yearMonth

            MonthColumn(
                year = year,
                month = month,
                days = dates,
                completedDays = completedDays,
                onDayClick = onDayClick,
                today = today
            )
        }
    }
}

@Preview
@Composable
fun CalendarGridPreview() {
    val sampleCalendar = Calendar(
        id = UUID.randomUUID(),
        title = "Preview Calendar",
        startDate = LocalDate.of(2026, 1, 1),
        durationDays = 365
    )

    val sampleDays = listOf(
        DayEntry(sampleCalendar.id, LocalDate.of(2026, 1, 1), true),
        DayEntry(sampleCalendar.id, LocalDate.of(2026, 1, 2), false),
        DayEntry(sampleCalendar.id, LocalDate.of(2026, 1, 3), true),
    )

    CalendarGrid(
        calendar = sampleCalendar,
        days = sampleDays,
        onDayClick = {}
    )
}

