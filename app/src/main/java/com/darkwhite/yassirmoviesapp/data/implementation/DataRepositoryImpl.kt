package com.darkwhite.yassirmoviesapp.data.implementation

import com.darkwhite.yassirmoviesapp.data.repository.DataRepository
import io.ktor.client.HttpClient
import javax.inject.Inject

class DataRepositoryImpl @Inject constructor(
  private val httpClient: HttpClient
) : DataRepository {

}