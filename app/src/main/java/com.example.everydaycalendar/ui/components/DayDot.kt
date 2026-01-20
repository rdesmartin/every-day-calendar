package com.example.everydaycalendar.ui.components

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.MaterialTheme
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp

@Composable
fun DayDot(
    onClick : () -> Unit,
    completed : Boolean,
    enabled: Boolean = true
) {
    val color = if (completed) {
        MaterialTheme.colorScheme.primary
    } else {
        MaterialTheme.colorScheme.surfaceVariant
    }

    Box(
        modifier = Modifier
            .size(12.dp)
            .alpha(if (enabled) 1f else 0.4f)
            .background(
                color = color,
                shape = CircleShape
            )
            .clickable(
                enabled = enabled,
                role = Role.Button,
                onClick = onClick
            )
    )}

@Preview(showBackground = true)
@Composable
fun DayDotCompletedPreview() {
    DayDot(
        completed = true,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun DayDotIncompletePreview() {
    DayDot(
        completed = false,
        onClick = {}
    )
}

@Preview(showBackground = true)
@Composable
fun DayDotDisabledPreview() {
    DayDot(
        completed = true,
        enabled = false,
        onClick = {}
    )
}