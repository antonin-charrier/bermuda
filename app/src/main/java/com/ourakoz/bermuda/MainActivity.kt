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
import kotlin.collections.ArrayList

class MainActivity : AppCompatActivity() {

    val genres: Array<Genre> = arrayOf(
        Genre("Heavy Metal"),
        Genre("Metal"),
        Genre("Thrash Metal"),
        Genre("Fusion"),
        Genre("Alternative Rock"),
        Genre("Neo Metal"),
        Genre("Indus Metal"),
        Genre("Progressive"),
        Genre("Groove Metal"),
        Genre("Rock")
    )
    val artists: Array<Artist> = arrayOf(
        Artist("Metallica", arrayListOf(
            genres?.first { g -> g.name == "Heavy Metal" },
            genres?.first { g -> g.name == "Metal" },
            genres?.first { g -> g.name == "Thrash Metal" }
        ), null),
        Artist("System Of A Down", arrayListOf(
            genres?.first { g -> g.name == "Alternative Rock" },
            genres?.first { g -> g.name == "Fusion" },
            genres?.first { g -> g.name == "Metal" }
        ), null),
        Artist("Korn", arrayListOf(
            genres?.first { g -> g.name == "Neo Metal" }
        ), null),
        Artist("Slipknot", arrayListOf(
            genres?.first { g -> g.name == "Neo Metal" },
            genres?.first { g -> g.name == "Metal" }
        ), null),
        Artist("Rammstein", arrayListOf(
            genres?.first { g -> g.name == "Indus Metal" }
        ), null),
        Artist("Deftones", arrayListOf(
            genres?.first { g -> g.name == "Metal" },
            genres?.first { g -> g.name == "Alternative Rock" },
            genres?.first { g -> g.name == "Neo Metal" },
            genres?.first { g -> g.name == "Rock" }
        ), null),
        Artist("Rage Against The Machine", arrayListOf(
            genres?.first { g -> g.name == "Fusion" }
        ), null),
        Artist("Tool", arrayListOf(
            genres?.first { g -> g.name == "Metal" },
            genres?.first { g -> g.name == "Progressive" }
        ), null),
        Artist("Machine Head", arrayListOf(
            genres?.first { g -> g.name == "Groove Metal" },
            genres?.first { g -> g.name == "Metal" },
            genres?.first { g -> g.name == "Thrash Metal" }
        ), null),
        Artist("Slayer", arrayListOf(
            genres?.first { g -> g.name == "Thrash Metal" }
        ), null),
        Artist("Pantera", arrayListOf(
            genres?.first { g -> g.name == "Groove Metal" }
        ), null),
        Artist("Sepultura", arrayListOf(
            genres?.first { g -> g.name == "Groove Metal" },
            genres?.first { g -> g.name == "Metal" },
            genres?.first { g -> g.name == "Thrash Metal" }
        ), null),
        Artist("Iron Maiden", arrayListOf(
            genres?.first { g -> g.name == "Heavy Metal" },
            genres?.first { g -> g.name == "Progressive" }
        ), null),
        Artist("Marilyn Manson", arrayListOf(
            genres?.first { g -> g.name == "Indus Metal" },
            genres?.first { g -> g.name == "Metal" },
            genres?.first { g -> g.name == "Rock" }
        ), null)
    )

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_artists.layoutManager = LinearLayoutManager(this)

        rv_artists.adapter = ArtistsAdapter(artists, this)
    }

    fun refreshSuggestions() {
        val suggestions = getSuggestions()
    }

    fun getSuggestions(): List<Artist> {
        val favoriteGenres = getFavoriteGenres()
        return artists.filter { a -> (a.genres.contains(favoriteGenres.first) || a.genres.contains(favoriteGenres.second)) && a.dateFavorite == null }
    }

    fun getFavoriteGenres(): Pair<Genre, Genre> {
        val genresFromAllArtists = arrayListOf<Genre>()
        artists.forEach { a -> genresFromAllArtists.addAll(a.genres) }

        val genresMapped = genresFromAllArtists.groupingBy { g -> g.name }.eachCount()
        var genre1: Map.Entry<String, Int>? = null
        var genre2: Map.Entry<String, Int>? = null

        for (genreMapped in genresMapped) {
            if (genre1 == null) {
                genre1 = genreMapped
                continue
            }
            if (genre2 == null) {
                if (genreMapped.value > genre1.value) {
                    genre2 = genre1;
                    genre1 = genreMapped
                }
                continue
            }

            if (genreMapped.value > genre1.value) {
                genre2 = genre1
                genre1 = genreMapped
                continue
            } else if (genreMapped.value > genre2.value) {
                genre2 = genreMapped
                continue
            }
        }

        return Pair(genres.first { g -> g.name == genre1?.key }, genres.first { g -> g.name == genre2?.key })
    }
}

data class Artist(val name: String, val genres: ArrayList<Genre>, var dateFavorite: LocalDateTime?)
data class Genre(val name: String)