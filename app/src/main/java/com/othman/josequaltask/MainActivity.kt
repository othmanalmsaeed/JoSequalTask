package com.othman.josequaltask

import android.annotation.SuppressLint
import android.content.Context
import android.graphics.*
import android.os.AsyncTask
import android.os.Bundle
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import com.google.android.gms.maps.CameraUpdateFactory
import com.google.android.gms.maps.GoogleMap
import com.google.android.gms.maps.OnMapReadyCallback
import com.google.android.gms.maps.SupportMapFragment
import com.google.android.gms.maps.model.BitmapDescriptor
import com.google.android.gms.maps.model.BitmapDescriptorFactory
import com.google.android.gms.maps.model.LatLng
import com.google.android.gms.maps.model.MarkerOptions
import com.google.maps.android.data.kml.KmlLayer
import com.google.maps.android.ui.IconGenerator
import com.othman.josequaltask.databinding.ActivityMainBinding
import com.othman.josequaltask.model.PlaceItem
import com.othman.josequaltask.utils.CustomInfoWindow
import com.othman.josequaltask.utils.CustomMarkerView
import com.othman.josequaltask.viewmodel.PlaceViewModel
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.URL


class MainActivity : AppCompatActivity(), OnMapReadyCallback {


    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mMap: GoogleMap
    private lateinit var placeViewModel: PlaceViewModel
    private lateinit var layer: KmlLayer
    private lateinit var imagesArray1: Array<Int>
    private lateinit var imagesArray2: Array<Int>


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)


        imagesArray1 = arrayOf(R.drawable.bondi1, R.drawable.cronulla1, R.drawable.manly1, R.drawable.maroubra1)
        imagesArray2 = arrayOf(R.drawable.bondi2, R.drawable.cronulla2, R.drawable.manly2, R.drawable.maroubra2)

        //Load Map
        val mapFragment = supportFragmentManager.findFragmentById(
            R.id.map_fragment
        ) as? SupportMapFragment
        mapFragment?.getMapAsync(this)


    }

    override fun onMapReady(googleMap: GoogleMap) {
        mMap = googleMap
        getPlaces()

        //Import KML file and show it in map
        layer = KmlLayer(mMap, R.raw.cluster, this@MainActivity)
        layer.addLayerToMap()
    }

    @SuppressLint("PotentialBehaviorOverride")
    private fun getPlaces() {


        placeViewModel = ViewModelProvider(this)[PlaceViewModel::class.java]

        //Call PlaceApi from ViewModel
        placeViewModel.getPlaceData()

        //LiveData Observer
        placeViewModel.placeItemList.observe(this, Observer {

            for (item in it) {
                val index = it.indexOf(item)
                val place = LatLng(item.lat.toDouble(), item.lng.toDouble())
                val markerIcon = getMarkerIcon(
                    root = findViewById(R.id.root),
                    markerImg = imagesArray1[index],
                    text = "2")
                mMap.addMarker(MarkerOptions().position(place).title(item.name).icon(markerIcon))
                mMap.moveCamera(CameraUpdateFactory.newLatLngZoom(place, 10F))
                mMap.setInfoWindowAdapter(CustomInfoWindow(applicationContext,imagesArray1[index], imagesArray2[index] ))

            }

        })


    }


    private fun getMarkerIcon(root: ViewGroup,markerImg: Int, text: String?): BitmapDescriptor? {
        val markerView = CustomMarkerView(root, markerImg, text)
        markerView.measure(View.MeasureSpec.UNSPECIFIED, View.MeasureSpec.UNSPECIFIED)
        markerView.layout(0, 0, markerView.measuredWidth, markerView.measuredHeight)
        markerView.isDrawingCacheEnabled = true
        markerView.invalidate()
        markerView.buildDrawingCache(false)
        return BitmapDescriptorFactory.fromBitmap(markerView.drawingCache)
    }




}



