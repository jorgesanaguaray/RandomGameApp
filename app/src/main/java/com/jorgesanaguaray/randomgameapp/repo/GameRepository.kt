package com.jorgesanaguaray.randomgameapp.repo

import com.jorgesanaguaray.randomgameapp.data.local.GameDao
import com.jorgesanaguaray.randomgameapp.data.local.GameEntity
import com.jorgesanaguaray.randomgameapp.data.remote.GameService
import com.jorgesanaguaray.randomgameapp.domain.GameItem
import com.jorgesanaguaray.randomgameapp.domain.toDomain
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GameRepository @Inject constructor(
    private val gameService: GameService,
    private val gameDao: GameDao
    ) {

    suspend fun getGamesFromApi(): List<GameItem> {

        val games = gameService.getGames()
        return games.map { it.toDomain() }

    }

    suspend fun getGamesFromDatabase(): List<GameItem> {

        val games = gameDao.getGames()
        return games.map { it.toDomain() }

    }

    suspend fun insertGames(games: List<GameEntity>) {
        gameDao.insertGames(games)
    }

    suspend fun deleteGames() {
        gameDao.deleteGames()
    }

}