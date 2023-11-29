package com.example.rustandroidsandbox

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.rustandroidsandbox.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        // Example of a call to a native method
        // binding.sampleText.text = stringFromJNI()
    }
}