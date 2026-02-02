package driven.roomdb

import domain.Calendar
import domain.DayEntry
import driven.roomdb.dao.CalendarDao
import driven.roomdb.dao.DayEntryDao
import driven.roomdb.entities.CalendarEntity
import driven.roomdb.entities.DayEntryEntity
import ports.driven.CalendarRepository
import java.time.LocalDate
import java.util.UUID
import javax.inject.Inject

class RoomCalendarRepository @Inject constructor(
    private val calendarDao: CalendarDao,
    private val dayEntryDao: DayEntryDao
) : CalendarRepository {

    override suspend fun list(): List<Calendar> =
        calendarDao.getAll().map { it.toDomain() }

    override suspend fun getById(calendarId: UUID): Calendar? =
        calendarDao.getById(calendarId)?.toDomain()

    override suspend fun create(calendar: Calendar) {
        calendarDao.insert(calendar.toEntity())
    }

    override suspend fun getDays(calendarId: UUID): List<DayEntry> {
        return dayEntryDao.getForCalendar(calendarId).map { it.toDomain() }
    }

    override suspend fun toggleDay(
        calendarId: UUID,
        date: LocalDate
    ): Boolean {
        val completed: Boolean = dayEntryDao.getDayEntry(calendarId, date)?.completed == true
        dayEntryDao.upsert(DayEntryEntity(calendarId, date, !completed))
        return !completed
    }

    override suspend fun getDayEntry(calendarId: UUID, date: LocalDate): DayEntry? {
        val dayEntry = dayEntryDao.getDayEntry(calendarId, date)
        return dayEntry?.toDomain()
    }
}

fun CalendarEntity.toDomain() = Calendar(id, title, startDate)
fun Calendar.toEntity() = CalendarEntity(id, title, startDate)

fun DayEntryEntity.toDomain() = DayEntry(calendarId, date, completed)
fun DayEntry.toEntity() = DayEntryEntity(calendarId, date, completed)

