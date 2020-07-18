package com.vanh.android.architecture.blueprints.whattodoapp.taskdetail

//import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.fragment.app.testing.launchFragmentInContainer
import androidx.test.ext.junit.runners.AndroidJUnit4
import androidx.test.filters.MediumTest
import com.vanh.android.architecture.blueprints.whattodoapp.R
import com.vanh.android.architecture.blueprints.whattodoapp.ServiceLocator
import com.vanh.android.architecture.blueprints.whattodoapp.data.FakeAndroidTestRepository
import com.vanh.android.architecture.blueprints.whattodoapp.data.Task
import com.vanh.android.architecture.blueprints.whattodoapp.data.source.TasksRepository
import kotlinx.coroutines.ExperimentalCoroutinesApi
import kotlinx.coroutines.test.runBlockingTest
import org.junit.After
import org.junit.Assert.*
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith

@ExperimentalCoroutinesApi
@MediumTest
@RunWith(AndroidJUnit4::class)
class TaskDetailFragmentTest {
    private lateinit var repository: TasksRepository

    @Before
    fun initRepository() {
        repository = FakeAndroidTestRepository()
        ServiceLocator.tasksRepository = repository
    }

    @After
    fun cleanupDb() = runBlockingTest {
        ServiceLocator.resetRepository()
    }

    @Test
    fun activeTaskDetails_DisplayedInUi() = runBlockingTest {
        // GIVEN - Add active (incomplete) task to the DB
        val activeTask = Task("Active Task", "AndroidX Rocks", false)

        // WHEN - Details fragment launched to display task
        val bundle = TaskDetailFragmentArgs(activeTask.id).toBundle()
        launchFragmentInContainer<TaskDetailFragment>(bundle, R.style.AppTheme)
        Thread.sleep(2000)

    }

}
