package com.example.everydaycalendar.ui.calendarmenu

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
    private val _calendarManager: CalendarManager,
) : ViewModel() {

    private val _calendars = MutableStateFlow<List<Calendar>>(emptyList())
    val calendars: StateFlow<List<Calendar>> = _calendars

    fun loadCalendars() {
        viewModelScope.launch {
            _calendars.value = _calendarManager.listCalendars()
        }
    }

    fun addCalendar(title: String, startDate : LocalDate = LocalDate.now()) {
        viewModelScope.launch {
            _calendarManager.createCalendar(
                title = title,
                startDate = startDate
            )
        }
        loadCalendars()
    }
}
