package com.darkwhite.yassirmoviesapp.data.di

import com.darkwhite.yassirmoviesapp.utils.Constants.EMPTY_STRING
import com.darkwhite.yassirmoviesapp.utils.Constants.TMDB_API_KEY
import com.darkwhite.yassirmoviesapp.utils.Constants.TMDB_BASE_URL
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import io.ktor.client.HttpClient
import io.ktor.client.engine.android.Android
import io.ktor.client.plugins.auth.Auth
import io.ktor.client.plugins.auth.providers.BearerTokens
import io.ktor.client.plugins.auth.providers.bearer
import io.ktor.client.plugins.contentnegotiation.ContentNegotiation
import io.ktor.client.plugins.defaultRequest
import io.ktor.serialization.kotlinx.json.json
import javax.inject.Singleton
import kotlinx.serialization.json.Json

@Module
@InstallIn(SingletonComponent::class)
object AppModule {
  
  @Singleton
  @Provides
  fun providesHttpClient(): HttpClient = HttpClient(Android) {
    defaultRequest { url(TMDB_BASE_URL) }
    install(ContentNegotiation) {
      json(
        Json {
          prettyPrint = true
          isLenient = true
          ignoreUnknownKeys = true
        }
      )
    }
    engine {
      connectTimeout = 5000
      socketTimeout = 5000
    }
    install(Auth) {
      bearer {
        loadTokens {
          BearerTokens(TMDB_API_KEY, EMPTY_STRING)
        }
      }
    }
  }
  
}