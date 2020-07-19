package com.vanh.android.architecture.blueprints.whattodoapp.tasks

import android.app.Application
import android.os.Build
import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import androidx.test.core.app.ApplicationProvider
import androidx.test.ext.junit.runners.AndroidJUnit4
import com.vanh.android.architecture.blueprints.whattodoapp.Event
import com.vanh.android.architecture.blueprints.whattodoapp.R
import com.vanh.android.architecture.blueprints.whattodoapp.data.Task
import com.vanh.android.architecture.blueprints.whattodoapp.data.source.FakeTestRepository
import com.vanh.android.architecture.blueprints.whattodoapp.getOrAwaitValue
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.TestCoroutineDispatcher
import kotlinx.coroutines.test.resetMain
import kotlinx.coroutines.test.setMain
import org.hamcrest.CoreMatchers.*
import org.hamcrest.MatcherAssert.assertThat
import org.junit.After
import org.junit.Before
import org.junit.Rule
import org.junit.Test
//import org.junit.jupiter.api.Assertions.*
//import org.junit.jupiter.api.Test
import org.junit.runner.RunWith
import org.robolectric.annotation.Config

@Config(sdk = [Build.VERSION_CODES.O_MR1])
internal class TasksViewModelTest{
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    private lateinit var tasksViewModel: TasksViewModel

    // Use a fake repository to be injected into the viewModel
    private lateinit var tasksRepository: FakeTestRepository

    @ExperimentalCoroutinesApi
    val testDispatcher: TestCoroutineDispatcher = TestCoroutineDispatcher()

    @ExperimentalCoroutinesApi
    @Before
    fun setupDispatcher() {
        Dispatchers.setMain(testDispatcher)
    }

    @ExperimentalCoroutinesApi
    @After
    fun tearDownDispatcher() {
        Dispatchers.resetMain()
        testDispatcher.cleanupTestCoroutines()
    }

    @Before
    fun setupViewModel(){
        // We initialise the tasks to 3, with one active and two completed
        tasksRepository = FakeTestRepository()
        val task1 = Task("Title1", "Description1")
        val task2 = Task("Title2", "Description2", true)
        val task3 = Task("Title3", "Description3", true)
        tasksRepository.addTasks(task1, task2, task3)

        tasksViewModel = TasksViewModel(tasksRepository)
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
    @Test
    fun completeTask_dataAndSnackbarUpdated() {
        // Create an active task and add it to the repository.
        val task = Task("Title", "Description")
        tasksRepository.addTasks(task)

        // Mark the task as complete task.
        tasksViewModel.completeTask(task, true)

        // Verify the task is completed.
       assertThat(tasksRepository.tasksServiceData[task.id]?.isCompleted, `is`(true))

        // Assert that the snackbar has been updated with the correct text.
        val snackbarText: Event<Int> =  tasksViewModel.snackbarText.getOrAwaitValue()
        assertThat(snackbarText.getContentIfNotHandled(), `is`(R.string.task_marked_complete))
    }
}