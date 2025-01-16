package com.kjprojects.crypto.Adapter
import android.graphics.Color
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kjprojects.crypto.Model.Model
import com.kjprojects.crypto.databinding.ViewholderStockBinding

class StockAdapter(private val dataList:ArrayList<Model>):
RecyclerView.Adapter<StockAdapter.ViewHolder>(){

    private val formatter =  DecimalFormat("###,###,###,###.##")

    class ViewHolder(val binding: ViewholderStockBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): StockAdapter.ViewHolder {
        val binding = ViewholderStockBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: StockAdapter.ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            stockNameTxt.text = item.name
            stockPriceTxt.text = "RM${formatter.format(item.price)}"
            changePercentTxt.text = "${item.changePercent}%"
            sparkLineLayout.setData(item.lineData)

            val changeColor = when {
                item.changePercent > 3 -> Color.parseColor("#12c737")
                item.changePercent < 3 -> Color.parseColor("#ff0000")
                else -> Color.WHITE
            }
            changePercentTxt.setTextColor(changeColor)
            sparkLineLayout.sparkLineColor = changeColor

            val picName = when(item.name){
                "NASDAQ100" -> "stock_1"
                "S&P 500" -> "stock_2"
                "Dow Jones" -> "stock_3"
                else -> ""
            }
            val drawableResourceId = holder.itemView.context.resources.getIdentifier(
                picName,"drawable",holder.itemView.context.packageName
            )

            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(logoImg)
        }
    }

    override fun getItemCount(): Int = dataList.size
}