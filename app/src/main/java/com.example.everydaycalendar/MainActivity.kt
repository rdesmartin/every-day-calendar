package com.example.everydaycalendar

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.example.everydaycalendar.ui.calendardetail.CalendarDetailScreen
import com.example.everydaycalendar.ui.calendarmenu.CalendarMenuScreen
import com.example.everydaycalendar.ui.calendarmenu.CalendarListViewModel
import com.example.everydaycalendar.ui.theme.EveryDayCalendarTheme
import dagger.hilt.android.AndroidEntryPoint
import ui.calendardetail.CalendarDetailViewModel
import java.util.UUID

@AndroidEntryPoint
class MainActivity : ComponentActivity() {
    private val calendarListViewModel: CalendarListViewModel by viewModels()
    private val calendarDetailViewModel: CalendarDetailViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        enableEdgeToEdge()
        setContent {
            EveryDayCalendarTheme() {
                val navController = rememberNavController()

                NavHost(
                    navController = navController,
                    startDestination = "calendarMenu"
                ) {
                    composable(
                        route = "calendarDetail/{calendarId}"
                    ) { backStackEntry ->
                        val id = UUID.fromString(
                            backStackEntry.arguments?.getString("calendarId")
                        )

                        CalendarDetailScreen(
                            calendarId = id,
                            viewModel = calendarDetailViewModel,
                            openCalendarMenu = {
                                navController.navigate("calendarMenu")
                            }
                        )
                    }

                    composable("calendarMenu") {
                        CalendarMenuScreen(
                            onCalendarClick = { id ->
                                navController.navigate("calendarDetail/$id") {
                                    popUpTo("calendarMenu") { inclusive = true }
                                }
                            },
                            viewModel = calendarListViewModel
                        )
                    }

                }
            }
        }
    }
}