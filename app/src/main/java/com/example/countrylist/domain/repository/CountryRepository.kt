package com.example.countrylist.domain.repository

import com.example.countrylist.data.model.CountryListDto
import retrofit2.Response

interface CountryRepository {
    suspend fun getCountries(): Response<CountryListDto>
}