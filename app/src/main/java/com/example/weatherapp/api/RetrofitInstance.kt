package com.example.weatherapp.api

import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

object RetrofitInstance {
    private const val Baseurl = "https://api.weatherapi.com"

    private fun getInstance(): Retrofit {
        return Retrofit.Builder().
        baseUrl(Baseurl).
        addConverterFactory(GsonConverterFactory.create()
            ).build()
    }

    val weatherapi: WeatherApi = getInstance().create(WeatherApi::class.java)
}