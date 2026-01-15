package ui.calendardetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.Calendar
import domain.DayEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import usecase.ForManagingCalendars
import java.time.LocalDate
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import usecase.ForTrackingDays

@HiltViewModel
class CalendarDetailViewModel @Inject constructor(
    private val _calendar: ForManagingCalendars,
    private val _days: ForTrackingDays
) : ViewModel() {

    private val _calendarDays = MutableStateFlow<List<DayEntry>>(emptyList())
    val calendarDays: StateFlow<List<DayEntry>> = _calendarDays

    private var currentCalendar: Calendar? = null

    fun loadCalendar(calendarId: java.util.UUID) {
        viewModelScope.launch {
            // Get calendar details (here, from listCalendars; you might want a getById)
            val calendar = _calendar.listCalendars().find { it.id == calendarId }
            if (calendar == null) {
                // handle error: calendar not found
                return@launch
            }
            currentCalendar = calendar

            // Load DayEntries from repo
            val days = _days.getDays(calendarId)

            _calendarDays.value = days
        }
    }

    fun toggleDay(date: LocalDate) {
        viewModelScope.launch {
            currentCalendar?.let {
                _days.toggleDay(it.id, date)
                // reload days after toggle
                _calendarDays.value = _days.getDays(it.id)
            }
        }
    }
}
