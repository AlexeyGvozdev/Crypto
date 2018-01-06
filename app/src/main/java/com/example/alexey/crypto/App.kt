package com.example.alexey.crypto

import android.app.Application
import com.example.alexey.crypto.NamesCrypto.Companion
import com.example.alexey.crypto.NamesCrypto.Companion.RUB
import com.example.alexey.crypto.NamesCrypto.Companion.XRP
import retrofit2.Retrofit
import retrofit2.adapter.rxjava2.RxJava2CallAdapterFactory
import retrofit2.converter.gson.GsonConverterFactory

/**
 * Created by Alexey on 04.01.2018.
 */
class App : Application() {

    lateinit var restApi: RestApi
    override fun onCreate() {
        super.onCreate()

        val retrofit = Retrofit.Builder()
                .baseUrl("https://api.exmo.com")
                .addCallAdapterFactory(RxJava2CallAdapterFactory.create())
                .addConverterFactory(GsonConverterFactory.create())
                .build()

        restApi = retrofit.create(RestApi::class.java)
    }
}