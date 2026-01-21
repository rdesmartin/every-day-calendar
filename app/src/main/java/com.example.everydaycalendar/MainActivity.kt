package com.example.everydaycalendar

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.everydaycalendar.ui.calendardetail.CalendarDetailScreen
import com.example.everydaycalendar.ui.calendarlist.CalendarListScreen
import com.example.everydaycalendar.ui.calendarlist.CalendarListViewModel
import com.example.everydaycalendar.ui.theme.EveryDayCalendarTheme
import dagger.hilt.android.AndroidEntryPoint
import domain.Calendar
import ui.calendardetail.CalendarDetailViewModel

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val calendarListViewModel: CalendarListViewModel by viewModels()
    private val calendarDetailViewModel: CalendarDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        calendarListViewModel.addCalendar("test")
        calendarListViewModel.calendars.let {
            Log.d("debug",it.toString())
        }

        enableEdgeToEdge()
        setContent {
            EveryDayCalendarTheme {
                CalendarDetailScreen(calendarListViewModel, calendarDetailViewModel)
//                Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
//                    Greeting(
//                        name = "Johnny",
//                        modifier = Modifier.padding(innerPadding)
//                    )
//                }
            }
        }
    }
}

@Composable
fun Greeting(name: String, modifier: Modifier = Modifier) {
    Surface(color = Color.Red) {
        Text(
            text = "Hello, my name is $name!",
            modifier = modifier.padding(24.dp)
        )
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    EveryDayCalendarTheme {
        Greeting("test")
    }
}