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

    /* SharedPreferences */
    var btnLeerPref:Button?=null
    var btnGuardarPref:Button?=null
    var etClave:EditText?=null
    var etValor:EditText?=null


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initUI()

        btnGuardar!!.setOnClickListener {

            val datos = etInfo!!.text.toString()

            //Validar que existe texto en info
            escribirDatos(datos)

        }

        btnLeer!!.setOnClickListener {

            tvMostrar!!.text = leerArchivo(nombreArchivo)
        }

        /* SharedPreferences */

        btnLeerPref!!.setOnClickListener {

            val claveABuscar = etClave!!.text.toString()
            val datoEncotrado = getDatos(claveABuscar)

            etValor!!.setText(datoEncotrado)

        }

        btnGuardarPref!!.setOnClickListener {

            val claveAGuardar = etClave!!.text.toString()
            val valorABuscar = etValor!!.text.toString()
            setDatos(claveAGuardar, valorABuscar)

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

    /*
    * Metodo para sharedPreferences
    *
    * */

    fun setDatos(clave:String, valor:String){

        val prefs = getPreferences(MODE_PRIVATE)
        prefs.edit().putString(clave, valor).apply()
    }

    fun getDatos(clave:String):String{

        val prefs = getPreferences(MODE_PRIVATE)
        val dato = prefs.getString(clave, "No se encontro: ${clave}")

        return dato.toString()
    }

    fun initUI(){

        btnGuardar = findViewById(R.id.BtnGuardar)
        etInfo = findViewById(R.id.etInfo)
        btnLeer = findViewById(R.id.btnLeer)
        tvMostrar = findViewById(R.id.tvMostrar)

        /* SharedPreferences */
        btnLeerPref = findViewById(R.id.btnLeerPrefs)
        btnGuardarPref = findViewById(R.id.btnGuardarPrefs)
        etClave = findViewById(R.id.etClave)
        etValor = findViewById(R.id.etValor)

    }
}