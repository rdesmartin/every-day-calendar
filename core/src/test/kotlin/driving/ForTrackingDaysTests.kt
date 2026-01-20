package driving

import fake.CalendarRepositoryFake
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate

class ForTrackingDaysTests {
    @Test
    fun `toggle day` () = runTest {
        // Init
        val startDate = LocalDate.of(2026, 1, 1)
        val repo = CalendarRepositoryFake()
        val create = ForManagingCalendars(repo)
        val daysManager = DaysTracker(repo)
        create.createCalendar("Pet the cats", startDate)
        val calendarManager = ForManagingCalendars(repo)
        calendarManager.createCalendar("Pet the cats", startDate)
        val calendar = calendarManager.listCalendars().first()

        // Act — mark day complete
        daysManager.toggleDay(calendar.id, startDate)

        var days = daysManager.getDays(calendar.id)

        // Assert — day marked completed
        assertEquals(1, days.size)
        assertTrue(days.first().completed)

        // Act — toggle again
        daysManager.toggleDay(calendar.id, startDate)

        days = daysManager.getDays(calendar.id)

        // Assert — day marked not completed
        assertEquals(1, days.size)
        assertFalse(days.first().completed)
    }
}
