package com.example.jetpack_compose_assignment_1

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.jetpack_compose_assignment_1.ui.theme.Jetpackcomposeassignment1Theme
import android.content.res.Configuration.UI_MODE_NIGHT_YES
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons.Filled
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.ui.res.stringResource
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material.icons.filled.KeyboardArrowDown

data class Course(
    val title: String,
    val code: String,
    val creditHours: Int,
    val description: String,
    val prerequisites: String
)

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            Jetpackcomposeassignment1Theme {
                CourseListScreen()
            }
        }
    }
}

@Composable
fun CourseCard(course: Course) {
    var isExpanded by rememberSaveable { mutableStateOf(false) }

    Card(
        modifier = Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .animateContentSize(
                animationSpec = spring(
                    dampingRatio = Spring.DampingRatioMediumBouncy,
                    stiffness = Spring.StiffnessLow
                )),
        elevation = CardDefaults.cardElevation(defaultElevation = 4.dp),
        colors = CardDefaults.cardColors(
            containerColor = MaterialTheme.colorScheme.primary
        )
    ) {
        Row(modifier = Modifier.padding(16.dp)){
            Column(modifier = Modifier
                .weight(1f)
                .padding(end = 8.dp)
            ) {
                Text(text = course.title, style = MaterialTheme.typography.titleLarge)
                Spacer(modifier = Modifier.height(4.dp))
                Row {
                    Text(text = "Code: ${course.code}", style = MaterialTheme.typography.bodyMedium)
                    Spacer(modifier = Modifier.width(16.dp))
                    Text(
                        text = "Credits: ${course.creditHours}",
                        style = MaterialTheme.typography.bodyMedium
                    )

                }
                if (isExpanded) {
                    Spacer(modifier = Modifier.height(8.dp))
                    Text(
                        text = "Description: ${course.description}",
                        style = MaterialTheme.typography.bodySmall
                    )
                    Spacer(modifier = Modifier.height(4.dp))
                    Text(
                        text = "Prerequisites: ${course.prerequisites}",
                        style = MaterialTheme.typography.bodySmall
                    )
                }

            }

            IconButton(onClick = { isExpanded = !isExpanded }) {
                Icon(
                    imageVector = if (isExpanded) Filled.KeyboardArrowUp else Filled.KeyboardArrowDown,
                    contentDescription = if (isExpanded) {
                        stringResource(R.string.show_less)
                    } else {
                        stringResource(R.string.show_more)
                    },
                    tint = MaterialTheme.colorScheme.onPrimary
                )
            }
        }
    }
}

@Composable
fun CourseListScreen(courses: List<Course>) {
    LazyColumn {
        items(courses) { course ->
            CourseCard(course = course)
        }
    }
}

@Preview(showBackground = true)
@Composable
fun CoursePreview() {
    Jetpackcomposeassignment1Theme {
        val courseList = listOf(
            Course(
                "Intro to Programming", "CS101", 3,
                "Learn the basics of coding using modern programming languages. This course introduces variables, conditionals, loops, and functions.",
                "None"
            ),
            Course(
                "Data Structures", "CS201", 5,
                "Understand common data structures such as arrays, linked lists, stacks, queues, trees, and hash maps. Emphasis on practical usage and implementation.",
                "CS101"
            ),
            Course(
                "Algorithms", "CS301", 4,
                "Learn about algorithm design and analysis, including sorting, searching, recursion, and complexity. Covers greedy, divide and conquer, and dynamic programming techniques.",
                "CS201"
            ),
            Course(
                "Operating Systems", "CS401", 4,
                "Explore operating system concepts including process management, memory allocation, file systems, and concurrency. Hands-on with OS simulations.",
                "CS301"
            )
        )
        CourseListScreen(courses = courseList)
    }
}