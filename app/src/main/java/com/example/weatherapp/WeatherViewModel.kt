package com.example.weatherapp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.weatherapp.api.Constant
import com.example.weatherapp.api.NetworkResponse
import com.example.weatherapp.api.RetrofitInstance
import com.example.weatherapp.api.WeatherModel
import kotlinx.coroutines.cancel
import kotlinx.coroutines.launch

class WeatherViewModel : ViewModel() {

    private val weatherapi = RetrofitInstance.weatherapi
    private val _weatherresult = MutableLiveData<NetworkResponse<WeatherModel>>()
    val weatherresult: LiveData<NetworkResponse<WeatherModel>> = _weatherresult

    fun getdata(city: String) {
        _weatherresult.value = NetworkResponse.Loading
        viewModelScope.launch {
            try {
                val response = weatherapi.getWeather(Constant.Apikey, city)
                if (response.isSuccessful) {
                    response.body()?.let {
                        _weatherresult.value = NetworkResponse.Success(it)
                    }
                } else {
                    _weatherresult.value = NetworkResponse.Failure("Failed To Load Data")
                }
            }
            catch(e: Exception) {
                _weatherresult.value = NetworkResponse.Failure("Failed To Load Data")
            }
        }

    }
}