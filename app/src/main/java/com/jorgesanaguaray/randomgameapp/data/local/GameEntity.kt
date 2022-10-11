package com.jorgesanaguaray.randomgameapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey
import com.jorgesanaguaray.randomgameapp.domain.GameItem

/**
 * Created by Jorge Sanaguaray
 */

@Entity(tableName = "game_table")
data class GameEntity(

    @PrimaryKey
    val id: Int?,
    val title: String?,
    val thumbnail: String?,
    val short_description: String?,
    val game_url: String?

)

fun GameItem.toDatabase() = GameEntity(id = id, title = title, thumbnail = thumbnail, short_description = short_description, game_url = game_url)
