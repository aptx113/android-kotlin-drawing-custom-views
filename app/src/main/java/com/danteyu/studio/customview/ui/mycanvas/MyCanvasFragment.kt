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
package com.danteyu.studio.customview.ui.mycanvas

import android.os.Build
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.View.SYSTEM_UI_FLAG_FULLSCREEN
import android.view.ViewGroup
import android.view.WindowInsets
import androidx.fragment.app.Fragment
import com.danteyu.studio.androidkotlindrawingcustomviews.R

/**
 * Created by George Yu in 7月. 2021.
 */
class MyCanvasFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val myCanvasView = MyCanvasView(requireContext())

        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.R) {
            val controller = myCanvasView.windowInsetsController
            controller?.hide(WindowInsets.Type.systemBars())
        } else {
            myCanvasView.systemUiVisibility = SYSTEM_UI_FLAG_FULLSCREEN
        }
        myCanvasView.contentDescription = getString(R.string.canvasContentDescription)
        return myCanvasView
    }
}
