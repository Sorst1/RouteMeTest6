package com.example.routemetest.ui.fragments

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import com.android.volley.Request
import com.android.volley.toolbox.StringRequest
import com.android.volley.toolbox.Volley
import com.example.routemetest.R
import com.example.routemetest.databinding.FragmentWeatherBinding
import com.example.routemetest.models.WeatherModel
import com.example.routemetest.utilities.showToast

import org.json.JSONObject



class WeatherFragment :  BaseChangeFragment(R.layout.fragment_weather) {
 private lateinit var mBinding: FragmentWeatherBinding
    private lateinit var mCity:String
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
    mBinding = FragmentWeatherBinding.inflate(inflater, container,false)


        return mBinding.root
    }

    private fun requestWeatherData(city: String) {
        val url = "https://api.weatherapi.com/v1/forecast.json?key=" +
                API_KEY +
                "&q=" +
                city +
                "&days=" +
                "3" +
                "&aqi=no&alerts=no"

        val queue = Volley.newRequestQueue(context)
        val request = StringRequest(
            Request.Method.GET,
            url,
            {
                    result -> parseWeatherData(result)
            },
            {
                    error -> Log.d("MyLog", "Error: $error" )
            }
        )
        queue.add(request)
    }
    private fun parseWeatherData(result: String){

        val mainObject = JSONObject(result)
        val item = WeatherModel(
            mainObject.getJSONObject("location").getString("name"),
            mainObject.getJSONObject("current").getString("last_updated"),
            mainObject.getJSONObject("current")
                .getJSONObject("condition").getString("text"),
            mainObject.getJSONObject("current").getString("temp_c"),
            "",
        "",
        )
        mBinding.weatherCity.setText(item.city)
        mBinding.weatherTime.setText(item.time)
        mBinding.weatherCondition.setText(item.condition)
        mBinding.weatherCurrentTemp.setText(item.currentTemp)
    }

     override fun change() {
         mCity = mBinding.weatherInputCity.text.toString()
         if (mCity.isEmpty()) {
             showToast("Поле пустое")
         } else { requestWeatherData(mCity)
         }
     }
    companion object {
        const val API_KEY ="cec22422e94f4b08aab211435221812"
    }
}