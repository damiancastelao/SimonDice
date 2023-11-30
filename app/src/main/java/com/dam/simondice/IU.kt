package com.dam.simondice


import android.util.Log
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import kotlinx.coroutines.launch
import kotlinx.coroutines.delay

/**
 * Interfaz de usuario
 */

@Composable
fun IU(vm: VM) {
    Botonera(vm)
    Boton(enum_color = Colores.CLASE_AMARILLO)
}

/**
 * Botones de colores
 */
@Composable
fun Botonera(vm:VM) {
    // definimos un scope para la IU
    val iuScope = rememberCoroutineScope()
    // definimos un texto para el botón
    var texto = remember { mutableStateOf("Click me!") }
    /**
     * Botón para probar las corutinas
     */
    Button(
        modifier = Modifier
            .size((120).dp, (40).dp),

        onClick = {

                vm.espera(2000L)
                Log.d("corutina", "IU no para!")
                iuScope.launch {
                    Log.d("corutina", "IU: voy a parar 2sgs")
                    texto.value = "Voy a parar"
                    delay(5000L)
                    Log.d("corutina", "Ahora sigo")
                    texto.value = "Sigo!"
                }
            
        }
        ){
        Text(text = texto.value, fontSize = 10.sp)
    }
}


@Composable
fun Boton(enum_color: Colores) {
    Button(
        // utilizamos el color del enum
        colors =  ButtonDefaults.buttonColors(enum_color.color.value),
        onClick = { /*TODO*/ },
        modifier = Modifier
            .padding(100.dp)
            .size((80).dp, (40).dp)
    ) {
        // utilizamos el texto del enum
        Text(text = enum_color.txt, fontSize = 10.sp)
    }
}
/**
 * Preview de la interfaz de usuario
 */

@Preview(showBackground = true)
@Composable
fun IUPreview() {
    val vm = VM()
    IU(vm)
}