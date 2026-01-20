package com.example.everydaycalendar.ui.calendarlist

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import domain.Calendar
import driving.CalendarManager
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject

@HiltViewModel
class CalendarListViewModel @Inject constructor(
    private val _calendar: CalendarManager,
) : ViewModel() {

    private val _calendars = MutableStateFlow<List<Calendar>>(emptyList())
    val calendars: StateFlow<List<Calendar>> = _calendars

    fun loadCalendars() {
        viewModelScope.launch {
            _calendars.value = _calendar.listCalendars()
        }
    }

    fun addCalendar(title: String) {
        viewModelScope.launch {
            _calendar.createCalendar(
                title = title,
                startDate = LocalDate.now()
            )
            loadCalendars()
        }
    }
}
