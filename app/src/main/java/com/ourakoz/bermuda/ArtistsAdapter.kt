package com.ourakoz.bermuda

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_artist.view.*
import java.time.LocalDateTime

class ArtistsAdapter(val artists: Array<Artist>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return artists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvArtistType?.text = artists.elementAt(position).name
        holder?.tvArtistsClick?.setOnClickListener({ v ->
            run {
                artists.first { a -> a.name == holder?.tvArtistType?.text }.toggleFavorite(holder?.tvArtistFav)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_artist, parent, false))
    }

    fun Artist.toggleFavorite(iv: ImageView) {
        if (this.dateFavorite == null ) {
            this.dateFavorite = LocalDateTime.now()
            iv.setImageResource(R.drawable.star_empty)
        } else {
            this.dateFavorite = null
            iv.setImageResource(R.drawable.star_full)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvArtistType = view.artist_name
    val tvArtistFav = view.fav
    val tvArtistsClick = view.clickable
}
