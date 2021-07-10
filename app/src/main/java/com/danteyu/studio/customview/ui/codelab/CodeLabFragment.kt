/*
 * Copyright 2021 Dante Yu
 *
 * Licensed under the Apache License, Version 2.0 (the "License");
 * you may not use this file except in compliance with the License.
 * You may obtain a copy of the License at
 *
 *     http://www.apache.org/licenses/LICENSE-2.0
 *
 * Unless required by applicable law or agreed to in writing, software
 * distributed under the License is distributed on an "AS IS" BASIS,
 * WITHOUT WARRANTIES OR CONDITIONS OF ANY KIND, either express or implied.
 * See the License for the specific language governing permissions and
 * limitations under the License.
 */
package com.danteyu.studio.customview.ui.codelab

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danteyu.studio.androidkotlindrawingcustomviews.R
import com.danteyu.studio.androidkotlindrawingcustomviews.databinding.FragCodelabBinding
import com.danteyu.studio.customview.ui.codelab.dial.DialFragment
import com.danteyu.studio.customview.ui.codelab.mycanvas.MyCanvasFragment
import com.google.android.material.tabs.TabLayoutMediator

/**
 * Created by George Yu in 7月. 2021.
 */
class CodeLabFragment : Fragment() {

    private lateinit var viewDataBinding: FragCodelabBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        viewDataBinding = FragCodelabBinding.inflate(layoutInflater, container, false)
        return viewDataBinding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val fragments = ArrayList<Fragment>().apply {
            add(DialFragment())
            add(MyCanvasFragment())
        }
        val titles = ArrayList<String>().apply {
            add(getString(R.string.dial))
            add(getString(R.string.my_canvas))
        }
        viewDataBinding.codeLabViewPager.adapter = CodeLabPagerAdapter(fragments, requireActivity())
        TabLayoutMediator(
            viewDataBinding.codeLabTab,
            viewDataBinding.codeLabViewPager
        ) { tab, position ->
            tab.text = titles[position]
        }.attach()
    }
}
