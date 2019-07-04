package com.ourakoz.bermuda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.provider.MediaStore
import android.widget.Button
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime
import java.util.*

class MainActivity : AppCompatActivity() {

    val genres: Array<Genre> = arrayOf(
        Genre("Heavy Metal"),
        Genre("Metal"),
        Genre("Trash Metal"),
        Genre("Fusion"),
        Genre("Alternative Rock"),
        Genre("Neo Metal"),
        Genre("Indus Metal"),
        Genre("Progressif"),
        Genre("Groove Metal")
    )
    val artists: Array<Artist> = arrayOf(
        Artist("Metallica", arrayListOf(
            genres.first { g -> g.name == "Heavy Metal" },
            genres.first { g -> g.name == "Metal" },
            genres.first { g -> g.name == "Trash Metal" }
        ), null),
        Artist("System Of A Down", arrayListOf(
            genres.first { g -> g.name == "Alternative Rock" },
            genres.first { g -> g.name == "Fusion" },
            genres.first { g -> g.name == "Metal" }
        ), null),
        Artist("Korn", arrayListOf(
            genres.first { g -> g.name == "Neo Metal" }
        ), null),
        Artist("Slipknot", arrayListOf(
            genres.first { g -> g.name == "Neo Metal" },
            genres.first { g -> g.name == "Metal" }
        ), null),
        Artist("Rammstein", arrayListOf(
            genres.first { g -> g.name == "Indus Metal" }
        ), null),
        Artist("Deftones", arrayListOf(
            genres.first { g -> g.name == "Metal" },
            genres.first { g -> g.name == "Alternative Rock" },
            genres.first { g -> g.name == "Neo Metal" },
            genres.first { g -> g.name == "Rock" }
        ), null),
        Artist("Rage Against The Machin", arrayListOf(
            genres.first { g -> g.name == "Fusion" }
        ), null),
        Artist("Tool", arrayListOf(
            genres.first { g -> g.name == "Metal" },
            genres.first { g -> g.name == "Progressif" }
        ), null),
        Artist("Machine Head", arrayListOf(
            genres.first { g -> g.name == "Groove Metal" },
            genres.first { g -> g.name == "Metal" },
            genres.first { g -> g.name == "Trash Metal" }
        ), null),
        Artist("Slayer", arrayListOf(
            genres.first { g -> g.name == "Trash Metal" }
        ), null),
        Artist("Pantera", arrayListOf(
            genres.first { g -> g.name == "Groove Metal" }
        ), null),
        Artist("Sepultura", arrayListOf(
            genres.first { g -> g.name == "Groove Metal" },
            genres.first { g -> g.name == "Metal" },
            genres.first { g -> g.name == "Trash metal" }
        ), null),
        Artist("Iron Maiden", arrayListOf(
            genres.first { g -> g.name == "Heavy Metal" },
            genres.first { g -> g.name == "Progressif" }
        ), null),
        Artist("Marilyn Manson", arrayListOf(
            genres.first { g -> g.name == "Indus Metal" },
            genres.first { g -> g.name == "Metal" },
            genres.first { g -> g.name == "Rock" }
        ), null)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_artists.layoutManager = LinearLayoutManager(this)

        rv_artists.adapter = ArtistsAdapter(artists, this)
    }
}

data class Artist(val name: String, val genres: ArrayList<Genre>, var dateFavorite: LocalDateTime?)
data class Genre(val name: String)