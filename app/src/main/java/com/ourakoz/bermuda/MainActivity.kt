package com.ourakoz.bermuda

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import java.time.LocalDateTime


class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        rv_artists.layoutManager = LinearLayoutManager(this)
        rv_artists.adapter = ArtistsAdapter(artists, this)
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.fav, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.action_suggestions -> {
                startActivity(Intent(this, SuggestionsActivity::class.java))
                true
            }
            else -> super.onOptionsItemSelected(item)
        }
    }

    companion object {
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
    }
}

data class Artist(val name: String, val genres: ArrayList<Genre>, var dateFavorite: LocalDateTime?)
data class Genre(val name: String)