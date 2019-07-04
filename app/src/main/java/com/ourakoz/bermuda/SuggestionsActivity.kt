package com.ourakoz.bermuda

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_suggestions.*

class SuggestionsActivity : AppCompatActivity() {

    lateinit var suggestionsList: Array<Artist>

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_suggestions)
        setTitle(R.string.suggestions)
        refreshSuggestions()

        rv_suggestions.layoutManager = LinearLayoutManager(this)
        rv_suggestions.adapter = ArtistsAdapter(suggestionsList, this)

    }

    fun refreshSuggestions() {
        suggestionsList = getSuggestions()
    }

    fun getSuggestions(): Array<Artist> {
        val favoriteGenres = getFavoriteGenres()
        return MainActivity.artists.filter { a -> (a.genres.contains(favoriteGenres.first) || a.genres.contains(favoriteGenres.second)) && a.dateFavorite == null }.toTypedArray()
    }

    fun getFavoriteGenres(): Pair<Genre?, Genre?> {
        val genresFromAllArtists = arrayListOf<Genre>()
        MainActivity.artists.forEach { a -> genresFromAllArtists.addAll(a.genres) }

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

        return Pair(MainActivity.genres.first { g -> g.name == genre1?.key }, MainActivity.genres.first { g -> g.name == genre2?.key })
    }
}
