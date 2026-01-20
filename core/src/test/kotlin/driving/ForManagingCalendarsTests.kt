package driven

import fake.CalendarRepositoryFake
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Test
import driving.ForManagingCalendars
import java.time.LocalDate

class ForManagingCalendarsTests {

    @Test
    fun `create calendar`() = runTest {
        // Init
        val startDate = LocalDate.of(2026, 1, 1)
        val repo = CalendarRepositoryFake()

        // Act - create calendar
        val calendarManager = ForManagingCalendars(repo)
        calendarManager.createCalendar("Pet the cats", startDate)

        // Assert — calendar exists
        val calendars = calendarManager.listCalendars()

        assertEquals(1, calendars.size)
        val calendar = calendars.first()
        assertEquals(calendar.title, "Pet the cats")
    }
}
