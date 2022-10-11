package com.jorgesanaguaray.randomgameapp.data.local

import androidx.room.Entity
import androidx.room.PrimaryKey

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
