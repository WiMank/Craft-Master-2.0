package com.wimank.craftmaster.tz.app.ui

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import com.bumptech.glide.request.target.Target.SIZE_ORIGINAL
import com.wimank.craftmaster.tz.R
import com.wimank.craftmaster.tz.app.di.modules.GlideApp
import com.wimank.craftmaster.tz.app.mvp.common.IMAGE_FOLDER_NAME
import kotlinx.android.synthetic.main.fragment_brewing.view.*
import java.io.File

class BrewingFragment : Fragment() {

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        val view = inflater.inflate(R.layout.fragment_brewing, container, false)
        val targetImage = File(
            view.context.getExternalFilesDir(IMAGE_FOLDER_NAME), "brewing.png"
        )

        GlideApp.with(view)
            .load(targetImage)
            .dontTransform()
            .override(SIZE_ORIGINAL, SIZE_ORIGINAL)
            .into(view.brewing_image)

        return view
    }
}
