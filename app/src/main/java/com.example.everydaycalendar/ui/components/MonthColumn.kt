package com.example.everydaycalendar.ui.components

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.height
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import java.time.LocalDate
import java.time.format.TextStyle
import java.util.Locale

@OptIn(ExperimentalLayoutApi::class)
@Composable
fun MonthColumn(
    year: Int,
    month: Int,
    days: List<LocalDate>,
    today: LocalDate,
    completedDays: Set<LocalDate>,
    onDayClick: (LocalDate) -> Unit
) {
    val monthName = LocalDate.of(year, month, 1)
        .month
        .getDisplayName(TextStyle.SHORT, Locale.getDefault())

    Column(
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Text(
            text = monthName,
            style = MaterialTheme.typography.labelMedium
        )

        Spacer(Modifier.height(8.dp))

        days.forEach { date ->
            DayDot(
                completed = completedDays.contains(date),
                onClick = { onDayClick(date) },
                enabled = date <= today
            )

            Spacer(Modifier.height(4.dp))
        }
    }
}
