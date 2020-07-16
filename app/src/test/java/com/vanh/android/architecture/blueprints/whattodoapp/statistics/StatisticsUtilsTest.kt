package com.vanh.android.architecture.blueprints.whattodoapp.statistics

import com.vanh.android.architecture.blueprints.whattodoapp.data.Task
import org.junit.jupiter.api.Assertions.*
import org.junit.jupiter.api.Test

internal class StatisticsUtilsTest{
    @Test
    fun getActiveAndCompletedStats_noCompleted_returnsHundredZero() {

        // no complete task and one active task
        // then there are 100% active task and 0% complete task
        // Create an active tasks (the false makes this active)
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = false)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(0f,result.completedTasksPercent)
        assertEquals(100f,result.activeTasksPercent)
    }
    @Test
    fun getActiveAndCompletedStats_both_return40n60of_CompleteActiveTask() {
        // if there 2 complete task and 3 active task
        // then there are 40% percent competed task and 60% active task
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(40f, result.completedTasksPercent)
        assertEquals(60f, result.activeTasksPercent)

    }
    @Test
    fun getActiveAndCompletedStats_both_return50n50of_CompleteActiveTask() {
        // if there 2 complete task and 2 active task
        // then there are 50% percent competed task and 50% active task
        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(50f, result.completedTasksPercent)
        assertEquals(50f, result.activeTasksPercent)
    }
    @Test
    fun getActiveAndCompletedStats_return0CompleteTask() {

        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(0f, result.completedTasksPercent)
      //  assertEquals(50f, result.activeTasksPercent)
    }
     @Test
    fun getActiveAndCompletedStats_return0ActiveTask() {
        // if there 2 complete task and 3 active task
        // then there are 40% percent competed task and 60% active task
        val tasks = listOf<Task>()
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(0f, result.activeTasksPercent)
      //  assertEquals(50f, result.activeTasksPercent)
    }
     @Test
    fun getActiveAndCompletedStats_return100CompleteTask() {

        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true),
            Task("title", "desc", isCompleted = true)
        )
        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(100f, result.completedTasksPercent)
      //  assertEquals(50f, result.activeTasksPercent)
    }
    @Test
    fun getActiveAndCompletedStats_return100ActiveTask() {

        val tasks = listOf<Task>(
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false),
            Task("title", "desc", isCompleted = false)
        )

        // Call our function
        val result = getActiveAndCompletedStats(tasks)

        // Check the result
        assertEquals(100f, result.activeTasksPercent)
      //  assertEquals(50f, result.activeTasksPercent)
    }
}