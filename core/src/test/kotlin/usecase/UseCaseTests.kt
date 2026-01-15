package repository

import fake.CalendarRepositoryFake
import kotlinx.coroutines.test.runTest
import org.junit.Assert.*
import org.junit.Test
import usecase.ForCreatingCalendar
import usecase.ForTogglingDay
import usecase.ForViewingCalendars
import java.time.LocalDate

class StubCalendarRepositoryTest {

    @Test
    fun `create calendar`() = runTest {
        // Init
        val startDate = LocalDate.of(2026, 1, 1)
        val repo = CalendarRepositoryFake()
        val listCalendars = ForViewingCalendars(repo)

        // Act - create calendar
        val create = ForCreatingCalendar(repo)
        create("Pet the cats", startDate)

        // Assert — calendar exists
        val calendars = listCalendars()

        assertEquals(1, calendars.size)
        val calendar = calendars.first()
        assertEquals(calendar.title, "Pet the cats")
    }

    @Test
    fun `toggle day` () = runTest {
        // Init
        val startDate = LocalDate.of(2026, 1, 1)
        val repo = CalendarRepositoryFake()
        val create = ForCreatingCalendar(repo)
        val toggleDay = ForTogglingDay(repo)
        create("Pet the cats", startDate)
        val listCalendars = ForViewingCalendars(repo)
        val calendar = listCalendars().first()

        // Act — mark day complete
        toggleDay(calendar.id, startDate)

        var days = toggleDay.getDays(calendar.id)

        // Assert — day marked completed
        assertEquals(1, days.size)
        assertTrue(days.first().completed)

        // Act — toggle again
        toggleDay(calendar.id, startDate)

        days = toggleDay.getDays(calendar.id)

        // Assert — day marked not completed
        assertEquals(1, days.size)
        assertFalse(days.first().completed)
    }
}
