package com.example.everydaycalendar.ui.calendardetail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import com.example.everydaycalendar.ui.calendarlist.CalendarListViewModel
import com.example.everydaycalendar.ui.components.CalendarGrid
import domain.Calendar
import ui.calendardetail.CalendarDetailViewModel
import java.util.UUID

@Composable
fun CalendarDetailScreen(
    calendarId: UUID,
    viewModel: CalendarDetailViewModel
) {
    val days by viewModel.calendarDays.collectAsState()
    val calendar by viewModel.currentCalendar.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.loadCalendar(calendarId)
    }

    calendar?.let {
        CalendarGrid(
            calendar = it,
            days = days,
            onDayClick = viewModel::toggleDay
        )
    }
}
