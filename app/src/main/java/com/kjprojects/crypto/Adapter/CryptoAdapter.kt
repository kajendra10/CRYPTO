package com.kjprojects.crypto.Adapter
import android.graphics.Color
import android.icu.text.DecimalFormat
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.kjprojects.crypto.Model.Model
import com.kjprojects.crypto.databinding.ViewholderCryptoBinding

class CryptoAdapter(private val dataList:ArrayList<Model>):
RecyclerView.Adapter<CryptoAdapter.ViewHolder>(){

    private val formatter =  DecimalFormat("###,###,###,###.##")

    class ViewHolder(val binding: ViewholderCryptoBinding):RecyclerView.ViewHolder(binding.root)

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): CryptoAdapter.ViewHolder {
        val binding = ViewholderCryptoBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return ViewHolder(binding)
    }

    override fun onBindViewHolder(holder: CryptoAdapter.ViewHolder, position: Int) {
        val item = dataList[position]
        holder.binding.apply {
            cryptoNameTxt.text = item.name
            cryptoPriceTxt.text = "RM${formatter.format(item.price)}"
            changePercentTxt2.text = "${item.changePercent}%"
            propertySizeTxt.text = "${item.propertySize}${item.symbol}"
            propertyAmountTxt.text = "RM${formatter.format(item.propertyAmount)}"
            sparkLineLayout.setData(item.lineData)

            val changeColor = when {
                item.changePercent > 3 -> Color.parseColor("#12c737")
                item.changePercent < 3 -> Color.parseColor("#ff0000")
                else -> Color.WHITE
            }
            changePercentTxt2.setTextColor(changeColor)
            sparkLineLayout.sparkLineColor = changeColor

            val drawableResourceId = holder.itemView.context.resources.getIdentifier(
                item.name,"drawable",holder.itemView.context.packageName
            )

            Glide.with(holder.itemView.context)
                .load(drawableResourceId)
                .into(logoImg2)
        }
    }

    override fun getItemCount(): Int = dataList.size
}