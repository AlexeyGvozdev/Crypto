package com.example.alexey.crypto

import retrofit2.http.GET
import java.util.*
import io.reactivex.Observable
import com.example.alexey.crypto.NamesCrypto
import retrofit2.http.Url


/**
 * Created by Alexey on 04.01.2018.
 */
interface RestApi {

    @GET()
    fun getNameCrypto(@Url way: String): Observable<List<String>>

    @GET()
    fun getPriceCrypto(@Url way: String): Observable<Data>
}