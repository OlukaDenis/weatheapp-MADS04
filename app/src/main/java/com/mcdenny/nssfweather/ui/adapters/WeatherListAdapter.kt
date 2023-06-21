package com.mcdenny.nssfweather.ui.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import coil.load
import com.mcdenny.nssfweather.R
import com.mcdenny.nssfweather.databinding.LayoutDailyWeatherItemBinding
import com.mcdenny.nssfweather.models.WeatherUiModel

class WeatherListAdapter: ListAdapter<WeatherUiModel, WeatherListAdapter.WeatherViewHolder>(COMPARATOR) {

    companion object {
        object COMPARATOR : DiffUtil.ItemCallback<WeatherUiModel>() {
            override fun areItemsTheSame(
                oldItem: WeatherUiModel,
                newItem: WeatherUiModel
            ): Boolean = oldItem.id == newItem.id

            override fun areContentsTheSame(
                oldItem: WeatherUiModel,
                newItem: WeatherUiModel
            ): Boolean  = oldItem.id == newItem.id
        }
    }

    inner class WeatherViewHolder(
        private val binding: LayoutDailyWeatherItemBinding
    ): RecyclerView.ViewHolder(binding.root) {

        fun bindItem(item: WeatherUiModel) {
            with(binding) {
                mtvDay.text = item.day
                mtvTemperature.text = item.temperature.current
                civWeatherIcon.load(item.condition?.icon) {
                    crossfade(true)
                    placeholder(R.drawable.clear)
                    error(R.drawable.clear)
                }
            }
        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): WeatherViewHolder {
        return WeatherViewHolder(
            LayoutDailyWeatherItemBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        )
    }

    override fun onBindViewHolder(holder: WeatherViewHolder, position: Int) {
       val item = getItem(position)
        holder.bindItem(item)
    }

}