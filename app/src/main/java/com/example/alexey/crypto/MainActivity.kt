package com.example.alexey.crypto

import android.support.v7.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.util.Log
import android.view.View
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity() {

    val adapter = MyAdapter()
    var listNamePrice: ArrayList<NamePrice> = ArrayList<NamePrice>()
    var i: Int = 0

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        loadCoins()
        refresh.setColorSchemeResources(R.color.red, R.color.blue, R.color.cyan)
        refresh.setOnRefreshListener { refreshCoins() }
//        loadNameCrypto()

    }
    private fun refreshCoins() {
        refresh.isRefreshing = true
        Handler().postDelayed( Runnable {
                loadCoins()
                refresh.isRefreshing = false
            }, 4500
        )

    }
    private fun loadCoins() {
        listNamePrice.clear()

        loadPriceCrypto(NamesCrypto.XRP, NamesCrypto.RUB)
        loadPriceCrypto(NamesCrypto.XRP, NamesCrypto.USD)
        loadPriceCrypto(NamesCrypto.BTC, NamesCrypto.USD)
        loadPriceCrypto(NamesCrypto.BTC, NamesCrypto.RUB)
    }

    private fun loadPriceCrypto(first: String,second: String) {
        val way: String = "./v1/order_book/?pair=${first}_${second}&limit=1"
        val restApi = (application as App).restApi
        restApi.getPriceCrypto(way)
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                        { fillRecycleView(it, first, second) },
                        { errorFill(it)}
                )
    }


    private fun fillRecycleView(it: Data, first: String, second: String) {
        val key: String = "${first}/${second}"

        val namePrice: NamePrice = NamePrice(key)

//        var arrLisr: ArrayList<String> = ArrayList<String>()
//        arrLisr.add(key)
        namePrice.price = when {
            first == NamesCrypto.XRP && second == NamesCrypto.RUB -> Math.round(it.XRP_RUB.bid_top * 100.0) / 100.0
            first == NamesCrypto.XRP && second == NamesCrypto.USD -> Math.round(it.XRP_USD.bid_top * 100.0) / 100.0
            first == NamesCrypto.BTC && second == NamesCrypto.USD -> Math.round(it.BTC_USD.bid_top * 100.0) / 100.0
            first == NamesCrypto.BTC && second == NamesCrypto.RUB -> Math.round(it.BTC_RUB.bid_top * 100.0) / 100.0
            else -> 0.0
        }
//        listCryptoNamePrice.add(arrLisr)
        listNamePrice.add(namePrice)
        i++
        if (i == 4) {
            Log.d("My tag", listNamePrice.toString())
//            tv_test.text = listNamePrice.toString()
            recycle_view.adapter = adapter
            recycle_view.layoutManager = LinearLayoutManager(this)
            recycle_view.setHasFixedSize(true)
            progress.visibility = View.GONE
            adapter.setCoinsList(listNamePrice)
            i = 0

        }
    }

    fun loadNameCrypto() {
        val restApi = (application as App).restApi
        restApi.getNameCrypto("./v1/currency/")
                .observeOn(AndroidSchedulers.mainThread())
                .subscribeOn(Schedulers.io())
                .subscribe(
                    { fillTextView(it) },
                    { errorFill(it) }
                )

    }

    private fun errorFill(it: Throwable){
        Log.d("My Tag", it.message)
    }

    private fun fillTextView(it: List<String>) {
        Log.d("My tag error", it.toString())
    }
}
