package com.example.currencyquoteapp.api

import retrofit2.Response
import retrofit2.http.GET

interface CotacaoAPI {

    //Criando Requisição:
    @GET("BTC/ticker/")
    suspend fun recuperarCotacaoBitcoin() : Response <Cotacao>

    @GET("ETH/ticker/")
    suspend fun recuperarCotacaoEthereum() : Response <Cotacao>

}