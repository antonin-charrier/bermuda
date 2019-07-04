package com.ourakoz.bermuda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {

    val genres: Array<Genre> = arrayOf(
        Genre("testGenre1"),
        Genre("testGenre2")
    )
    val artists: Array<Artist> = arrayOf(
        Artist("testArtist1", arrayListOf(
            genres.first { g -> g.name == "testGenre1" }
        ), null),
        Artist("testArtist1", arrayListOf(
            genres.first { g -> g.name == "testGenre2" }
        ), null)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var favBtn = findViewById<Button>(R.id.fav)
        favBtn.setOnClickListener({ v -> artists.first { a -> a.name == "testArtist1" }.toggleFavorite() })
    }

    fun Artist.toggleFavorite() {
        if (this.dateFavorite == null ) this.dateFavorite = LocalDateTime.now() else this.dateFavorite = null
    }
}

data class Artist(val name: String, val genres: ArrayList<Genre>, var dateFavorite: LocalDateTime?)
data class Genre(val name: String)