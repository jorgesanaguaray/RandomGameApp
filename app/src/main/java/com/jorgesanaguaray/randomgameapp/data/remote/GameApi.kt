package com.jorgesanaguaray.randomgameapp.data.remote

import com.jorgesanaguaray.randomgameapp.util.Constants.Companion.GAMES
import retrofit2.Response
import retrofit2.http.GET

/**
 * Created by Jorge Sanaguaray
 */

interface GameApi {

    @GET(GAMES)
    suspend fun getGames(): Response<List<GameModel>>

}