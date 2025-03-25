package com.example.countrylist.data.repository

import com.example.countrylist.data.model.CountryListDto
import com.example.countrylist.data.remote.ApiService
import com.example.countrylist.domain.repository.CountryRepository
import retrofit2.Response

class CountryRepositoryImpl(private val apiService: ApiService): CountryRepository {
    override suspend fun getCountries(): Response<CountryListDto> {
        return apiService.getCountries()
    }
}