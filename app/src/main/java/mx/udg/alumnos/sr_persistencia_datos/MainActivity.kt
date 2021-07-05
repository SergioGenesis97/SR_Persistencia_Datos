package mx.udg.alumnos.sr_persistencia_datos

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import android.widget.TextView
import java.io.FileOutputStream
import java.io.InputStream
import java.lang.Exception

class MainActivity : AppCompatActivity() {

    val nombreArchivo =  "mi_archivo.txt"

    var btnGuardar:Button?=null
    var etInfo:EditText?=null
    var btnLeer:Button?=null
    var tvMostrar:TextView?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        btnGuardar = findViewById(R.id.BtnGuardar)
        etInfo = findViewById(R.id.etInfo)
        btnLeer = findViewById(R.id.btnLeer)
        tvMostrar = findViewById(R.id.tvMostrar)

        btnGuardar!!.setOnClickListener {

            val datos = etInfo!!.text.toString()

            //Validar que existe texto en info
            escribirDatos(datos)

        }

        btnLeer!!.setOnClickListener {

            tvMostrar!!.text = leerArchivo(nombreArchivo)
        }

    }

    fun escribirDatos(texto:String){
        val fos:FileOutputStream

        try {
            fos = openFileOutput(nombreArchivo, Context.MODE_PRIVATE)
            fos.write(texto.toByteArray())
            fos.close()
            Log.wtf("Archivos: ", texto)

        }catch (e:Exception){
            Log.wtf("Archivos: ", "Error! ${e.message}")
            e.printStackTrace()
        }
    }

    fun leerArchivo(nombreArchivo:String):String{

        val inputStream:InputStream = openFileInput(nombreArchivo)

        val strinResultado = inputStream.bufferedReader().use { it.readLine() }
        Log.d("Archivos: ", strinResultado)
        return strinResultado

    }
}