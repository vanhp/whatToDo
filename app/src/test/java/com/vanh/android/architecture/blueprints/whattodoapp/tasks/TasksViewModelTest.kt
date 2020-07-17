package com.vanh.android.architecture.blueprints.whattodoapp.tasks

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vanh.android.architecture.blueprints.whattodoapp.getOrAwaitValue
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.Before
import org.junit.Rule
import org.junit.Test
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@RunWith(AndroidJUnit4::class)
@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class TasksViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()
    private lateinit var tasksViewModel: TasksViewModel

    @Before
    fun setupViewModel(){
        tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
    }

    @Test
    fun addNewTask_setNewTaskEvent(){
        // Given a fresh TasksViewModel
       // val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())

        // When adding a new task
        tasksViewModel.addNewTask()

        // Then the new task event is triggered
        val value = tasksViewModel.newTaskEvent.getOrAwaitValue()
        assertThat(value.getContentIfNotHandled(),(not(nullValue())))
    }
     @Test
    fun setFilterAllTasks_tasksAddViewVisible() {

        // Given a fresh ViewModel
       // val tasksViewModel = TasksViewModel(ApplicationProvider.getApplicationContext())
        // When the filter type is ALL_TASKS
         tasksViewModel.setFiltering(TasksFilterType.ALL_TASKS)

        // Then the "Add task" action is visible
         val value  = tasksViewModel.tasksAddViewVisible.getOrAwaitValue()
        assertThat(value,`is`(true))
    }
}