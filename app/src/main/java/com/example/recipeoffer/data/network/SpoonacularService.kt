package com.example.recipeoffer.data.network

import okhttp3.OkHttpClient
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class SpoonacularService {

    private val mainOkHttpClient = OkHttpClient()

    private val recipesOkHttpClient = mainOkHttpClient.newBuilder()
        .addInterceptor { chain ->
            val url = chain.request().url()
                .newBuilder()
                .addQueryParameter("apiKey", SP_API_KEY)
                .build()
            val req = chain.request().newBuilder().url(url).build()
            chain.proceed(req)
        }
        .build()

    private val retrofit: Retrofit = Retrofit.Builder()
        .baseUrl(SP_BASE_URL)
        .addConverterFactory(GsonConverterFactory.create())
        .client(recipesOkHttpClient)
        .build()

    val recipesApi: RecipesApi = retrofit.create(RecipesApi::class.java)

    companion object {
        private const val SP_BASE_URL: String = "https://api.spoonacular.com/"
        private const val SP_API_KEY: String = "e1b57e35f5184b198917839cda8e7926"
    }


}