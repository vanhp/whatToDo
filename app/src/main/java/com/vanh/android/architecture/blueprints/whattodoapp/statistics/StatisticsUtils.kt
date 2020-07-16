/*
 * Copyright (C) 2019 The Android Open Source Project
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *      http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */

package com.vanh.android.architecture.blueprints.whattodoapp.statistics

import com.vanh.android.architecture.blueprints.whattodoapp.data.Task

/**
 * Function that does some trivial computation. Used to showcase unit tests.
 */
internal fun getActiveAndCompletedStats(tasks: List<Task>?): StatsResult {
    val totalTasks = tasks!!.size
    val numberOfActiveTasks = tasks.count { it.isActive }
    val x = 100f * numberOfActiveTasks / tasks.size
    return StatsResult(
        activeTasksPercent = if(tasks.size != 0)
                                100f * numberOfActiveTasks / tasks.size
                             else 0.0f,
        completedTasksPercent = if(tasks.size != 0)
                                    100f * (totalTasks - numberOfActiveTasks) / tasks.size
                                else 0.0f
    )
}

data class StatsResult(val activeTasksPercent: Float, val completedTasksPercent: Float)
