package com.example.countrylist.domain.usecase

import com.example.countrylist.data.model.CountryListDto
import com.example.countrylist.data.model.CountryListDtoItem
import com.example.countrylist.domain.repository.CountryRepository
import com.example.countrylist.domain.util.Result
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow

class GetCountriesUseCase(private val countryRepository: CountryRepository) {

    suspend operator fun invoke(): Flow<Result<CountryListDto>> = flow {

        emit(Result.Loading)
        val result = countryRepository.getCountries()
        if (result.isSuccessful) {
            result.body()?.let {
                emit(Result.Success(it))
            } ?: emit(Result.Error("No Data found in response"))
        } else {
            emit(Result.Error(result.message()))
        }
    }

}