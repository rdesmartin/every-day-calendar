package repository

import domain.Calendar
import kotlinx.coroutines.test.runTest
import org.junit.Assert.assertEquals
import org.junit.Assert.assertFalse
import org.junit.Assert.assertTrue
import org.junit.Test
import java.time.LocalDate
import java.util.UUID

class StubCalendarRepositoryTest {
    @Test
    fun `create calendar`() = runTest {
        // Arrange
        val repo = StubCalendarRepository()

        val startDate = LocalDate.of(2026, 1, 1)
        val testCalendar = Calendar(UUID.randomUUID(), "Pet the cats", startDate)

        // Act — create calendar
        repo.create(testCalendar)
        val calendars = repo.list()

        // Assert — calendar exists
        assertEquals(1, calendars.size)
        val calendar = calendars.first()
        assertEquals(calendar.id, testCalendar.id)
    }

    @Test
    fun `toggle day` () = runTest {
        // Setup
        val repo = StubCalendarRepository()
        val startDate = LocalDate.of(2026, 1, 1)
        val calendar = Calendar(UUID.randomUUID(), "Pet the cats", startDate)
        repo.create(calendar)

        // Act — toggle day
        repo.toggleDay(calendar.id, startDate)

        var days = repo.getDays(calendar.id)

        // Assert — day marked completed
        assertEquals(1, days.size)
        assertTrue(days.first().completed)

        // Act — toggle again
        repo.toggleDay(calendar.id, startDate)

        days = repo.getDays(calendar.id)

        // Assert — day marked not completed
        assertEquals(1, days.size)
        assertFalse(days.first().completed)
    }
}
