package com.othman.josequaltask.utils

import android.view.View
import android.view.ViewGroup
import android.widget.FrameLayout
import android.widget.ImageView
import android.widget.TextView
import com.othman.josequaltask.R

class CustomMarkerView(
    root: ViewGroup,
    text: String?
) : FrameLayout(root.context) {
    private var mImage: ImageView
    private var mTitle: TextView

    init {
        View.inflate(context, R.layout.custom_marker_layout, this)
        mImage = findViewById(R.id.marker_image)
        mTitle = findViewById(R.id.marker_title)
        measure(MeasureSpec.UNSPECIFIED, MeasureSpec.UNSPECIFIED)
        mTitle.text = text
        mImage.setImageResource(R.drawable.firebase)

    }
}