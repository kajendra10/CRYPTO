package com.kjprojects.crypto.Activity
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.view.WindowManager
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import com.kjprojects.crypto.Adapter.CryptoAdapter
import com.kjprojects.crypto.Adapter.StockAdapter
import com.kjprojects.crypto.Model.Model
import com.kjprojects.crypto.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    private val lineData = arrayListOf(1000, 1100, 1200, 1100)
    private val lineData2 = arrayListOf(2100, 2000, 1900, 2000)
    private val lineData3 = arrayListOf(900, 1100, 1200, 1000, 1150)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        window.setFlags(
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS,
            WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS
        )
        window.clearFlags(WindowManager.LayoutParams.FLAG_TRANSLUCENT_STATUS)
        window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS)
        window.statusBarColor = Color.WHITE
        window.decorView.systemUiVisibility= View.SYSTEM_UI_FLAG_VISIBLE

        cryptoList()
        stockList()

    }

    private fun stockList() {
        binding.stockView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.HORIZONTAL, false)
        val domainArrayList = arrayListOf(
            Model("NASDAQ100","BTX",3465.67,3.15,lineData,3465.67,0.324),
            Model("S&P 500","ETH",4785.87,2.10,lineData2,4785.87,0.473),
            Model("Dow Jones","RIX",2893.40,1.57,lineData3,2893.40,0.225)
        )
        binding.stockView.adapter = StockAdapter(domainArrayList)
    }

    private fun cryptoList() {
        binding.cryptoView.layoutManager = LinearLayoutManager(this, LinearLayoutManager.VERTICAL, false)
        //Using sample data for practice. For actual application, use real data with API Services

        val domainArrayList = arrayListOf(
            Model("bitcoin","BTX",3465.67,3.15,lineData,3465.67,0.324),
            Model("etherium","ETH",4785.87,2.10,lineData2,4785.87,0.473),
            Model("trox","RIX",2893.40,1.57,lineData3,2893.40,0.225)
        )
        binding.cryptoView.adapter = CryptoAdapter(domainArrayList)
    }
}