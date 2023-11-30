package com.example.rustandroidsandbox

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    init {
        System.loadLibrary("hello_world")
    }

    external fun parseAddress(address: String): String
}