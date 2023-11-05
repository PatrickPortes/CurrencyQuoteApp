package com.example.currencyquoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.currencyquoteapp.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    //Atributos:

    //viewBinding:
    private lateinit var binding: ActivityMainBinding

    private lateinit var btRecuperar: Button
    private lateinit var tvBitcoin: TextView
    private lateinit var tvEthereum: TextView

    //Métodos:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewBinding:
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startingComponents()

        btRecuperar.setOnClickListener {

            //Recuperar Dados da Api
            TODO()
        }
    }

    private fun startingComponents() {
        btRecuperar = binding.btRecuperar
        tvBitcoin = binding.tvBitcoin
        tvEthereum = binding.tvEthereum
    }
}