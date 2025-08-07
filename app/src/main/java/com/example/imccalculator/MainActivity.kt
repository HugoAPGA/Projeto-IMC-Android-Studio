package com.example.imccalculator

import android.os.Bundle
import android.view.View
import android.widget.EditText
import android.widget.TextView
import android.widget.Toast
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat

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

    fun calcularImc(view: View) {
        val textResultado = findViewById<TextView>(R.id.textResultado)
        val editPeso = findViewById<EditText>(R.id.editPeso)
        val editAltura = findViewById<EditText>(R.id.editAltura)

        val peso = editPeso.getText().toString()
        val altura = editAltura.getText().toString()

        if (peso.isEmpty() && altura.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha ambos os campos.", Toast.LENGTH_SHORT).show()
        } else if (peso.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o campo Peso.", Toast.LENGTH_SHORT).show()
        } else if (altura.isEmpty()) {
            Toast.makeText(this, "Por favor, preencha o campo Altura.", Toast.LENGTH_SHORT).show()
        } else {
            val pesoConvertido: Double = peso.toDouble()
            val alturaConvertido: Double = altura.toDouble()

            val resultadoImc = pesoConvertido / (alturaConvertido * alturaConvertido)

            var classificacao = ""

            if (resultadoImc < 18.5) {
                classificacao = "baixo peso"
            } else if (resultadoImc <= 24.9) {
                classificacao = "peso ideal"
            } else if (resultadoImc <= 29.9) {
                classificacao = "sobrepeso"
            } else if (resultadoImc < 34.9) {
                classificacao = "obesidade grau I"
            } else if (resultadoImc < 39.9) {
                classificacao = "obesidade grau II (severa)"
            } else {
                classificacao = "obesidade grau III (mórbida)"
            }

            val resultadoImcFormatado = "%.2f".format(resultadoImc)

            textResultado.setText("O seu IMC é: $resultadoImcFormatado. Você está com $classificacao.")
        }
    }
}