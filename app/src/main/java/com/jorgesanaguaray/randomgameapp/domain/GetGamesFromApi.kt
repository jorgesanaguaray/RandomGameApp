package com.jorgesanaguaray.randomgameapp.domain

import com.jorgesanaguaray.randomgameapp.data.local.toDatabase
import com.jorgesanaguaray.randomgameapp.repo.GameRepository
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GetGamesFromApi @Inject constructor(private val gameRepository: GameRepository) {

    suspend operator fun invoke(): List<GameItem> {

        val games = gameRepository.getGamesFromApi()

        if (games.isNotEmpty()) {
            gameRepository.deleteGames()
            gameRepository.insertGames(games.map { it.toDatabase() })
        }

        return games

    }

}