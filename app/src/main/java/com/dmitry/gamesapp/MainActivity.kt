package com.dmitry.gamesapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.navigation.findNavController
import androidx.navigation.ui.setupActionBarWithNavController

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        //Установка экшен-бара (названия фрагментов в шапке)
        //setupActionBarWithNavController(findNavController(R.id.container_view))
    }
}