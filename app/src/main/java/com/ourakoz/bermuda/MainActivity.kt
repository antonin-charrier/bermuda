package com.ourakoz.bermuda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
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
    }
}

data class Artist(val name: String, val genres: ArrayList<Genre>, val dateFavorite: Date?)
data class Genre(val name: String)