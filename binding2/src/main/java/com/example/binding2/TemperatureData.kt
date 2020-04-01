package com.example.binding2

import androidx.databinding.BaseObservable
import androidx.databinding.Bindable



class TemperatureData(private var location: String, private var celsius: String,
                      private var url: String) :
    BaseObservable() {
    @Bindable
    fun getCelsius(): String = celsius

    @Bindable
    fun getLocation(): String = location

    @Bindable
    fun getUrl():String = url

    fun setLocation(location: String) {
        this.location = location
        notifyPropertyChanged(BR.location)
    }

    fun setCelsius(celsius: String) {
        this.celsius = celsius
        notifyPropertyChanged(BR.celsius)
    }

    fun setUrl(url: String){
        this.url = url
        notifyPropertyChanged(BR.url)
    }

}