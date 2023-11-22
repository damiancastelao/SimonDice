package com.dam.simondice

import android.util.Log
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

/**
 * ViewModel del juego
 */
class VM : ViewModel() {
    /**
     * Definimos una funcion con la corutina
     */
    fun espera(segundos: Long){
        viewModelScope.launch {
            Log.d("corutina","Esperando en el Viewmodel...")
            delay(segundos)
            Log.d("corutina","Listo en el ViewModel!")
        }
    }
    /**
     * Genera un número aleatorio entre 0 y maxima-1
     * @param maximo Número máximo generado
     * @return Número aleatorio
     */
    fun generarNumeroAleatorio(maximo: Int): Int {
        return (0..maximo - 1).random()
    }

    /**
     * Inicializo el juego
     */
    fun inicializarJuego() {
        reiniciarRonda()
        reiniciarSecuencia()
        reiniciarSecuenciaUsuario()
        Datos.estado = Estado.INICIO
    }

    /**
     * Aumenta la secuencia de colores
     * muestra al usuario la secuencia de colores
     */
    fun estadoSecuencia() {
        Datos.estado = Estado.SECUENCIA
        aumentarSecuencia()
        mostrarSecuencia()
        Datos.estado = Estado.ESPERANDO
    }

    /**
     * aumenta un color a la secuencia
     */
    fun aumentarSecuencia() {
        Datos.estado = Estado.SECUENCIA
        Datos.secuencia.add(generarNumeroAleatorio(4))
    }

    /**
     * Muestra la secuencia de colores
     */
    private fun mostrarSecuencia() {
        TODO("Not yet implemented")
    }

    /**
     * Aumentar la secuencia del usuario
     * @param color Color introducido por el usuario
     */
    fun aumentarSecuenciaUsuario(color: Int){
        Datos.estado = Estado.ENTRADA
        Datos.secuenciaUsuario.add(color)
    }

    /**
     * Comprueba si la secuencia introducida por el usuario es correcta
     * @return True si es correcta, false si no lo es
     */
    fun comprobarSecuencia() {
        Datos.estado = Estado.COMPROBANDO
        if (Datos.secuencia == Datos.secuenciaUsuario) {
            Datos.ronda++
            if (Datos.ronda > Datos.record) {
                Datos.record = Datos.ronda
            }
            reiniciarSecuenciaUsuario()
            aumentarSecuencia()
        } else {
            Datos.estado = Estado.FINALIZADO
        }
    }


    /**
     * Reinicia la secuencia introducida por el usuario
     */
    fun reiniciarSecuenciaUsuario() {
        Datos.secuenciaUsuario = mutableListOf()
    }

    /**
     * Reinicia la secuencia
     */
    fun reiniciarSecuencia() {
        Datos.secuencia = mutableListOf()
    }

    /**
     * Reinicia la ronda
     */
    fun reiniciarRonda() {
        Datos.ronda = 0
    }

}