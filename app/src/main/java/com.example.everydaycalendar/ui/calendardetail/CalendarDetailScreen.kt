package com.example.everydaycalendar.ui.calendardetail

import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.remember
import androidx.compose.runtime.getValue
import com.example.everydaycalendar.ui.calendarlist.CalendarListViewModel
import com.example.everydaycalendar.ui.components.CalendarGrid
import domain.Calendar
import ui.calendardetail.CalendarDetailViewModel

@Composable
fun CalendarDetailScreen(
    calendarListViewModel: CalendarListViewModel,
    viewModel: CalendarDetailViewModel
) {
    val calendars by calendarListViewModel.calendars.collectAsState()
    val calendar: Calendar? = remember { viewModel.currentCalendar }
    val days by viewModel.calendarDays.collectAsState()

    calendar?.let {
        CalendarGrid(
            calendar = it,
            days = days,
            onDayClick = viewModel::toggleDay
        )
    }
}
