package com.example.everydaycalendar.ui.calendarlist

import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp

@Composable
fun CalendarListScreen(
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
        OutlinedTextField(
            value = title,
            onValueChange = { title = it },
            label = { Text("Calendar title") },
            modifier = Modifier.fillMaxWidth()
        )

        Spacer(Modifier.height(8.dp))

        Button(onClick = {
            viewModel.addCalendar(title)
            title = ""
        }) {
            Text("Create calendar")
        }

        Spacer(Modifier.height(16.dp))

        LazyColumn {
            items(calendars) { calendar ->
                Text(
                    text = calendar.title,
                    style = MaterialTheme.typography.bodyLarge,
                    modifier = Modifier.padding(8.dp)
                )
            }
        }
    }
}
