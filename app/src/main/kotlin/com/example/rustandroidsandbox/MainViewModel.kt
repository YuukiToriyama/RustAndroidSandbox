package com.example.rustandroidsandbox

import androidx.lifecycle.ViewModel

class MainViewModel : ViewModel() {
    init {
        System.loadLibrary("hello_world")
    }

    external fun sayHello(name: String): String
}