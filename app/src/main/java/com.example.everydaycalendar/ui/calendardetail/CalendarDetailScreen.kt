package com.example.everydaycalendar.ui.calendardetail

import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import com.example.everydaycalendar.ui.components.CalendarGrid
import com.example.everydaycalendar.ui.components.CalendarHeader
import ui.calendardetail.CalendarDetailViewModel
import java.util.UUID

@Composable
fun CalendarDetailScreen(
    calendarId: UUID,
    viewModel: CalendarDetailViewModel,
    openCalendarMenu: () -> Unit
) {
    val days by viewModel.calendarDays.collectAsState()
    val calendar by viewModel.currentCalendar.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCalendar(calendarId)
    }

    calendar?.let {
        CalendarHeader(
            title = it.title,
            onClick = openCalendarMenu
        )
        CalendarGrid(
            calendar = it,
            days = days,
            onDayClick = viewModel::toggleDay
        )
    }
}
