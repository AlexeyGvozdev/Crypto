package com.example.alexey.crypto

/**
 * Created by Alexey on 04.01.2018.
 */
data class DataCrypto(val ask_quantity: Double ,
                 val ask_amount: String,
                 val ask_top: String,
                 val bid_quantity: String,
                 val bid_amount: String,
                 val bid_top: Double,
                 val ask: List<List<String>>,
                 val bid: List<List<String>>)