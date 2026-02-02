package com.example.everydaycalendar.ui.calendarmenu

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.util.UUID

@Composable
fun CalendarMenuScreen(
    onCalendarClick: (UUID) -> Unit,
    viewModel: CalendarListViewModel
) {
    val calendars by viewModel.calendars.collectAsState()
    var title by remember { mutableStateOf("") }

    LaunchedEffect(Unit) {
        viewModel.loadCalendars()
    }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(16.dp)
    ) {
        Text(
            text = "Calendars",
            style = MaterialTheme.typography.titleLarge
        )
        Spacer(Modifier.height(16.dp))
        LazyColumn {
            items(calendars) { calendar ->
                Text(
                    text = calendar.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier
                        .padding(8.dp)
                        .clickable {
                            Log.d("test", calendar.durationDays.toString())
                            onCalendarClick(calendar.id)
                        }
                )
            }
        }

        TextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Calendar title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            viewModel.addCalendar(title, startDate = LocalDate.of(2026, 1, 1))
            title = ""
        }) {
            Text("Create calendar")
        }

        Spacer(Modifier.height(16.dp))
    }
}