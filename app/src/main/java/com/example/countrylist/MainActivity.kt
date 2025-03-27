package com.example.countrylist

import android.os.Bundle
import android.widget.ProgressBar
import androidx.activity.enableEdgeToEdge
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.countrylist.data.remote.RetrofitHelper
import com.example.countrylist.data.repository.CountryRepositoryImpl
import com.example.countrylist.databinding.ActivityMainBinding
import com.example.countrylist.domain.usecase.GetCountriesUseCase
import com.example.countrylist.domain.util.Result
import com.example.countrylist.ui.CountryListAdapter
import com.example.countrylist.ui.CountryViewModel

class MainActivity : AppCompatActivity() {
    private lateinit var binding: ActivityMainBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        ViewCompat.setOnApplyWindowInsetsListener(findViewById(R.id.main)) { v, insets ->
            val systemBars = insets.getInsets(WindowInsetsCompat.Type.systemBars())
            v.setPadding(systemBars.left, systemBars.top, systemBars.right, systemBars.bottom)
            insets
        }
        val viewModel = CountryViewModel(
            GetCountriesUseCase(
                CountryRepositoryImpl(
                    RetrofitHelper.createRetrofitService()
                )
            )
        )
        val progressBar: ProgressBar = binding.progressBar
        viewModel.getCountries()
        viewModel.liveData.observe(this) { countries ->
           when (countries) {
               is Result.Error -> {}
               Result.Idle -> {}
               Result.Loading -> {
                   progressBar.visibility = ProgressBar.VISIBLE
               }
               is Result.Success -> {
                   progressBar.visibility = ProgressBar.GONE
                   countries.data?.let {
                       val adapter =  CountryListAdapter(it)
                       val recycleView = binding.recycleView
                       recycleView.layoutManager = LinearLayoutManager(this)
                      binding.recycleView.adapter = adapter
                       adapter.notifyDataSetChanged()
                   }
               }
           }
       }
    }
    override fun onSaveInstanceState(outState: Bundle) {
        super.onSaveInstanceState(outState)
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
    }
}