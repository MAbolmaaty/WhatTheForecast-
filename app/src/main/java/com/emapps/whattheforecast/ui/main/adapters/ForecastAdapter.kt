package com.emapps.whattheforecast.ui.main.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.emapps.whattheforecast.R
import com.emapps.whattheforecast.data.model.ForecastDay
import com.emapps.whattheforecast.databinding.ViewHolderForecastDayBinding

class ForecastAdapter(private var forecast: List<ForecastDay>) :
    RecyclerView.Adapter<ForecastAdapter.ViewHolder>() {

    class ViewHolder(
        private val itemBinding: ViewHolderForecastDayBinding
    ) : RecyclerView.ViewHolder(itemBinding.root) {

        fun bindDay(forecastDay: ForecastDay) {
            itemBinding.txtDay.text = forecastDay.date
            Glide.with(itemBinding.root.context)
                .load("https:/${forecastDay.dayDetails.condition.icon}").into(itemBinding.icCondition)
            itemBinding.txtMinTemp.text =
                itemBinding.root.resources.getString(R.string.temp_celsius_degree)
                    .format(forecastDay.dayDetails.minCelsiusTemp.toInt())
            itemBinding.txtMaxTemp.text =
                itemBinding.root.resources.getString(R.string.temp_celsius_degree)
                    .format(forecastDay.dayDetails.maxCelsiusTemp.toInt())
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val itemBinding = ViewHolderForecastDayBinding.inflate(
            LayoutInflater.from(parent.context),
            parent, false
        )
        return ViewHolder(itemBinding)
    }

    override fun getItemCount() = forecast.size

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder.bindDay(forecast[position])
    }

    fun changeForecast(newForecast: List<ForecastDay>) {
        this.forecast = newForecast
        notifyDataSetChanged()
    }
}