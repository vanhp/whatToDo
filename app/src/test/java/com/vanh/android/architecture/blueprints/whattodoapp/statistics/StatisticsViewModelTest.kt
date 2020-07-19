package com.vanh.android.architecture.blueprints.whattodoapp.statistics

import androidx.arch.core.executor.testing.InstantTaskExecutorRule
import com.vanh.android.architecture.blueprints.whattodoapp.data.source.FakeTestRepository
import com.vanh.android.architecture.blueprints.whattodoapp.data.source.MainCoroutineRule
import com.vanh.android.architecture.blueprints.whattodoapp.getOrAwaitValue
import kotlinx.coroutines.ExperimentalCoroutinesApi
import org.hamcrest.CoreMatchers.`is`
import org.junit.Assert.*
import org.junit.Before
import org.junit.Rule
import org.junit.Test

@ExperimentalCoroutinesApi
class StatisticsViewModelTest {

    // Executes each task synchronously using Architecture Components.
    @get:Rule
    var instantExecutorRule = InstantTaskExecutorRule()

    // Set the main coroutines dispatcher for unit testing.
    @ExperimentalCoroutinesApi
    @get:Rule
    var mainCoroutineRule = MainCoroutineRule()

    // Subject under test
    private lateinit var statisticsViewModel: StatisticsViewModel

    // Use a fake repository to be injected into the view model.
    private lateinit var tasksRepository: FakeTestRepository

    @Before
    fun setupStatisticsViewModel() {
        // Initialise the repository with no tasks.
        tasksRepository = FakeTestRepository()

        statisticsViewModel = StatisticsViewModel(tasksRepository)
    }

    @Test
fun loadTasks_loading() {
        // normally the testing of coroutine usually run to completion
        // using runBlocking api but in a case where the value of interest
        // is occur while the coroutine is running so it need tobe paused
    //testing coroutine need to pause the coroutine to inspect value
    // Pause dispatcher so you can verify initial values.
    mainCoroutineRule.pauseDispatcher()

    // Load the task in the view model.
    statisticsViewModel.refresh()

    // Then progress indicator is shown.
    assertThat(statisticsViewModel.dataLoading.getOrAwaitValue(), `is`(true))

        // Execute pending coroutines actions.
    mainCoroutineRule.resumeDispatcher()

        // Then progress indicator is hidden.
    assertThat(statisticsViewModel.dataLoading.getOrAwaitValue(), `is`(false))
}

}