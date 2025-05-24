package com.example.icms

import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast


class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(R.layout.activity_main)
        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
    }

    fun calcular(view: View) {
        val estado = findViewById<EditText>(R.id.editTextEstado).text.toString().uppercase()
        val valorTexto = findViewById<EditText>(R.id.editTextValor).text.toString()

        if (estado.length != 2 || valorTexto.isBlank()) {
            Toast.makeText(this, "Preencha todos os campos corretamente.", Toast.LENGTH_SHORT).show()
            return
        }

        val valor = valorTexto.toDoubleOrNull()
        if (valor == null) {
            Toast.makeText(this, "Valor inválido.", Toast.LENGTH_SHORT).show()
            return
        }

        val icms = obterICMS(estado)
        val valorFinal = valor + (valor * icms / 100)

        findViewById<TextView>(R.id.textViewPorcentagem).text = "$icms%"
        findViewById<TextView>(R.id.textViewTotal).text = "R$ %.2f".format(valorFinal)
    }

    fun obterICMS(sigla: String): Double {
        return when (sigla) {
            "SP" -> 18.0
            "RJ" -> 20.0
            "MG" -> 18.0
            "ES" -> 17.0
            "BA" -> 18.0
            else -> 18.0
        }
    }
}