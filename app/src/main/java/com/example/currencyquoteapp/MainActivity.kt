package com.example.currencyquoteapp

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView
import com.example.currencyquoteapp.api.CotacaoAPI
import com.example.currencyquoteapp.databinding.ActivityMainBinding
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class MainActivity : AppCompatActivity() {

    //Atributos:

    //viewBinding:
    private lateinit var binding: ActivityMainBinding

    //layout views:
    private lateinit var btRecuperar: Button
    private lateinit var tvBitcoin: TextView
    private lateinit var tvEthereum: TextView

    //Retrofit
    val cotacaoAPI: CotacaoAPI by lazy {

        //Configuração Básica de um Retrofit Acessando uma API:
        Retrofit.Builder()
            .baseUrl("https://www.mercadobitcoin.net/api/")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
            .create(CotacaoAPI::class.java)
    }

    //Métodos:
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        //viewBinding:
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        startingComponents()

        //Recuperar Dados da Api
        btRecuperar.setOnClickListener {

            recuperarPrecoBitcoin()
            recuperarPrecoEthereum()

        }
    }

    private fun recuperarPrecoBitcoin() {

        //Cria uma Tarefa Assíncrona: (O App Precisa de Permissão de Internet para Executar)
        CoroutineScope(Dispatchers.IO).launch {

            //response da api:
            val resposta = cotacaoAPI.recuperarCotacaoBitcoin()
            //verifica se a response está vazia:
            if (resposta.isSuccessful) {
                //body = conteúdo da response (cotação)
                val cotacao = resposta.body()
                if (cotacao != null) {

                    /*Exibe a Cotação na Tela: (Precisa ser a MainThread
                      e não a IO que é a criada pelo desenvolvedor, com
                      isso o withContext muda para a principal)*/

                    withContext(Dispatchers.Main) {

                        tvBitcoin.text = "Bitcoin R$ ${cotacao.ticker.last}"

                    }

                }
            }
        }

    }

    private fun recuperarPrecoEthereum() {

        CoroutineScope(Dispatchers.IO).launch {

            val resposta = cotacaoAPI.recuperarCotacaoEthereum()
            if (resposta.isSuccessful) {

                val cotacao = resposta.body()
                if (cotacao != null) {
                    withContext(Dispatchers.Main) {
                        tvEthereum.text = "Ethereum R$ ${cotacao.ticker.last}"
                    }
                }
            }
        }
    }

    private fun startingComponents() {
        btRecuperar = binding.btRecuperar
        tvBitcoin = binding.tvBitcoin
        tvEthereum = binding.tvEthereum
    }
}