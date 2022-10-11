package com.jorgesanaguaray.randomgameapp.domain

import com.jorgesanaguaray.randomgameapp.repo.GameRepository
import javax.inject.Inject

/**
 * Created by Jorge Sanaguaray
 */

class GetGamesFromDatabase @Inject constructor(private val gameRepository: GameRepository) {

    suspend operator fun invoke(): List<GameItem> {

        return gameRepository.getGamesFromDatabase()

    }

}