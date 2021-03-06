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

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import com.vanh.android.architecture.blueprints.whattodoapp.R
import com.vanh.android.architecture.blueprints.whattodoapp.WhatTodoApplication
import com.vanh.android.architecture.blueprints.whattodoapp.data.source.DefaultTasksRepository
//import com.vanh.android.architecture.blueprints.whattodoapp.WhatTodoApplication
import com.vanh.android.architecture.blueprints.whattodoapp.databinding.StatisticsFragBinding
import com.vanh.android.architecture.blueprints.whattodoapp.util.setupRefreshLayout
import com.vanh.android.architecture.blueprints.whattodoapp.data.source.TasksRepository
/**
 * Main UI for the statistics screen.
 */
class StatisticsFragment : Fragment() {

    private lateinit var viewDataBinding: StatisticsFragBinding
//private val viewModel by viewModels<TasksViewModel>()
    private val viewModel by viewModels<StatisticsViewModel>{
//         StatisticsViewModelFactory(
//                 (requireContext().applicationContext as WhatTodoApplication).taskRepository)
    StatisticsViewModelFactory(DefaultTasksRepository.getRepository(requireActivity().application))
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        viewDataBinding = DataBindingUtil.inflate(inflater, R.layout.statistics_frag, container, false)
        return viewDataBinding.root
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewDataBinding.viewmodel = viewModel
        viewDataBinding.lifecycleOwner = this.viewLifecycleOwner
        this.setupRefreshLayout(viewDataBinding.refreshLayout)
    }
}
