package com.example.countrylist.ui

import android.annotation.SuppressLint
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.example.countrylist.data.model.CountryListDtoItem
import com.example.countrylist.R
import com.example.countrylist.data.model.CountryListDto
import com.example.countrylist.databinding.RowCountryViewBinding

class CountryListAdapter(private val countries: CountryListDto) :
    RecyclerView.Adapter<CountryListAdapter.CountryViewHolder>() {
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): CountryViewHolder {
        Log.d("MY_TAG", "Creating view holder")
        val binding =
            RowCountryViewBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return CountryViewHolder(binding)
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

    class CountryViewHolder(val binding: RowCountryViewBinding) :
        RecyclerView.ViewHolder(binding.root) {
        @SuppressLint("SetTextI18n")
        fun bind(country: CountryListDtoItem) {
            binding.countryName.text = country.name + ", "
            binding.countryRegion.text = country.region
            binding.countryCode.text = country.code
            binding.countryCapital.text = country.capital
        }
    }
}