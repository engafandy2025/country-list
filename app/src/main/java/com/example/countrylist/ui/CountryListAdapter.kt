package com.example.countrylist.ui

import android.util.Log
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countrylist.data.model.CountryListDtoItem
import com.example.countrylist.R
import com.example.countrylist.data.model.CountryListDto

class CountryListAdapter(private val countries: CountryListDto) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        Log.d("MY_TAG", "Creating view holder")
       val itemView:View = ViewGroup.inflate(parent.context, R.layout.row_country_view, null)
        return CountryViewHolder(itemView)
    }

    override fun onBindViewHolder(
        holder: CountryViewHolder,
        position: Int
    ) {
        holder.bind(countries[position])
    }

    override fun getItemCount(): Int {
        return countries.size
    }
    class CountryViewHolder( itemView: View) : RecyclerView.ViewHolder(itemView) {
        fun bind(country: CountryListDtoItem) {
            itemView.findViewById<TextView>(R.id.country_name).text = country.name +", "
            itemView.findViewById<TextView>(R.id.country_region).text = country.region
            itemView.findViewById<TextView>(R.id.country_code).text = country.code
            itemView.findViewById<TextView>(R.id.country_capital).text = country.capital
        }
    }
}