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
package com.danteyu.studio.customview.ui.codelab.shaders

import android.app.AlertDialog
import android.app.Dialog
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.danteyu.studio.androidkotlindrawingcustomviews.R
import com.danteyu.studio.androidkotlindrawingcustomviews.databinding.FragShadersBinding

/**
 * Created by George Yu in 7月. 2021.
 */
class ShadersFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {

        val viewDataBinding = FragShadersBinding.inflate(layoutInflater, container, false)
        val dialog = createInstructionsDialog()
        dialog.show()
        return viewDataBinding.root
    }

    private fun createInstructionsDialog(): Dialog {
        val builder = AlertDialog.Builder(requireContext())
        builder.setIcon(R.drawable.ic_android)
            .setTitle(R.string.instructions_title)
            .setMessage(R.string.instructions)
            .setPositiveButton("start") { _, _ -> }
        return builder.create()
    }
}
