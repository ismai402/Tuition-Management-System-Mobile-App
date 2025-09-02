package com.example.edututor.ui

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.example.edututor.R
import com.example.edututor.di.AppModule

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        AppModule.init(applicationContext)
        setContentView(R.layout.activity_main)
    }
}
