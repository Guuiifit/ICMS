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
            "AC" -> 19.0
            "AL" -> 20.0
            "AP" -> 18.0
            "AM" -> 20.0
            "BA" -> 20.5
            "CE" -> 20.0
            "DF" -> 20.0
            "ES" -> 17.0
            "GO" -> 19.0
            "MA" -> 23.0
            "MT" -> 17.0
            "MS" -> 17.0
            "MG" -> 18.0
            "PA" -> 19.0
            "PB" -> 20.0
            "PR" -> 19.5
            "PE" -> 20.5
            "PI" -> 22.5
            "RJ" -> 22.0
            "RN" -> 20.0
            "RS" -> 17.0
            "RO" -> 19.5
            "RR" -> 20.0
            "SC" -> 17.0
            "SP" -> 18.0
            "SE" -> 20.0
            "TO" -> 20.0
            else -> 18.0
        }
    }
}