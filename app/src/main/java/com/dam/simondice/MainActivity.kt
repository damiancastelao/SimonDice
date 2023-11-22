package com.dam.simondice

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent


/**
 * Videojuego Simon Dice
 */
class MainActivity : ComponentActivity() {
    val TAG = "Juego MainActivity"
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
                // Interface de Usuario
                IU()
        }

        // test
        /*val vm = VM()
        for (color in Colores.values()) {
            Log.d(TAG,color.ordinal.toString())
        }*/
    }
}

