package com.example.countrylist.data.remote

import com.example.countrylist.data.model.CountryListDto
import retrofit2.Response
import retrofit2.http.GET

interface ApiService {

    @GET("32dcb892b06648910ddd40406e37fdab/raw")
    suspend fun getCountries(): Response<CountryListDto>
}