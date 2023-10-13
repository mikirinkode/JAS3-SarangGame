package com.mikirinkode.saranggame.data

import com.mikirinkode.saranggame.utils.Constants
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

interface ContainerInterface {
    val gameRepository: RepositoryInterface
}

class AppContainer : ContainerInterface {

    private val baseUrl = Constants.BASE_URL

    private val retrofit: Retrofit = Retrofit.Builder()
        .addConverterFactory(GsonConverterFactory.create())
        .baseUrl(baseUrl)
        .build()

    private val retrofitService: GameApiService by lazy {
        retrofit.create(GameApiService::class.java)
    }

    override val gameRepository: RepositoryInterface by lazy {
        GameRepository(retrofitService)
    }
}