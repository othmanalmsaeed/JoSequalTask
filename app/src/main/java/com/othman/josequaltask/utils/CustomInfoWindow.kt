package com.othman.josequaltask.utils

import android.content.Context
import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.widget.ImageView
import android.widget.TextView
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.model.Marker
import com.othman.josequaltask.R

class CustomInfoWindow(context: Context, var placeImg1: Int, var placeImg2: Int) : GoogleMap.InfoWindowAdapter {
    var window: View = LayoutInflater.from(context).inflate(R.layout.custom_info_window, null)



    private fun setInfoWindow(marker: Marker) {
        val title = marker.title
        val tvTitle = window.findViewById<TextView>(R.id.tvTitle)
        val img1 = window.findViewById<ImageView>(R.id.img1)
        val img2 = window.findViewById<ImageView>(R.id.img2)

        if (!TextUtils.isEmpty(title)) {
            tvTitle.text = title
            img1.setImageResource(placeImg1)
            img2.setImageResource(placeImg2)
        }
    }

    override fun getInfoWindow(p0: Marker): View {
        setInfoWindow(p0)
        return window
    }

    override fun getInfoContents(p0: Marker): View {
        setInfoWindow(p0)
        return window
    }
}