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

class CustomInfoWindow(mContext: Context) : GoogleMap.InfoWindowAdapter {
    var mWindow: View = LayoutInflater.from(mContext).inflate(R.layout.custom_info_window, null)

    private fun setInfoWindowText(marker: Marker) {
        val title = marker.title
        val tvTitle = mWindow.findViewById<TextView>(R.id.tvTitle)
        val img1 = mWindow.findViewById<ImageView>(R.id.img1)
        val img2 = mWindow.findViewById<ImageView>(R.id.img2)

        if (!TextUtils.isEmpty(title)) {
            tvTitle.text = title
            img1.setImageResource(R.drawable.firebase)
            img2.setImageResource(R.drawable.firebase)
        }
    }

    override fun getInfoWindow(p0: Marker): View {
        setInfoWindowText(p0)
        return mWindow
    }

    override fun getInfoContents(p0: Marker): View {
        setInfoWindowText(p0)
        return mWindow
    }
}