package com.example.countrylist.ui

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.countrylist.data.model.CountryListDto
import com.example.countrylist.domain.usecase.GetCountriesUseCase

import com.example.countrylist.domain.util.Result
import kotlinx.coroutines.launch

class CountryViewModel(private val getCountriesUseCase: GetCountriesUseCase) :
    ViewModel() {

    private val _liveData = MutableLiveData<Result<CountryListDto>>()
    val liveData: LiveData<Result<CountryListDto>> = _liveData


    fun getCountries() {
        viewModelScope.launch {
            getCountriesUseCase().collect {
                _liveData.value = it
            }
        }
    }


}