package ui.calendardetail

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import domain.Calendar
import domain.DayEntry
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.time.LocalDate
import javax.inject.Inject
import dagger.hilt.android.lifecycle.HiltViewModel
import driving.CalendarManager
import driving.DaysTracker
import ports.driving.ForManagingCalendars
import ports.driving.ForTrackingDays

@HiltViewModel
class CalendarDetailViewModel @Inject constructor(
    private val _calendarManager: CalendarManager,
    private val _daysTracker: DaysTracker
) : ViewModel() {

    private val _calendarDays = MutableStateFlow<List<DayEntry>>(emptyList())
    val calendarDays: StateFlow<List<DayEntry>> = _calendarDays


    var currentCalendar: Calendar? = null

    fun loadCalendar(calendarId: java.util.UUID) {
        viewModelScope.launch {
            // Get calendar details (here, from listCalendars; you might want a getById)
            val calendar =
                _calendarManager.getById(calendarId) ?: // handle error: calendar not found
                return@launch
            currentCalendar = calendar

            // Load DayEntries from repo
            val days = _daysTracker.getDays(calendarId)

            _calendarDays.value = days
        }
    }

    fun toggleDay(date: LocalDate) {
        viewModelScope.launch {
            currentCalendar?.let {
                _daysTracker.toggleDay(it.id, date)
                // reload days after toggle
                _calendarDays.value = _daysTracker.getDays(it.id)
            }
        }
    }
}
