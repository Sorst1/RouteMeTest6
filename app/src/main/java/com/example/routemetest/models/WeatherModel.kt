package com.example.routemetest.models


data class WeatherModel(
    val city:String="",
    var time:String="",
    var condition:String="",
    var currentTemp:String="",
    var maxTemp:String="",
    var minTemp:String=""
)