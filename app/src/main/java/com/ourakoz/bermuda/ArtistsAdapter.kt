package com.ourakoz.bermuda

import android.provider.MediaStore
import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import kotlinx.android.synthetic.main.item_artist.view.*
import java.time.LocalDateTime

class ArtistsAdapter(val artists: Array<Artist>, val context: Context) : RecyclerView.Adapter<ViewHolder>() {
    override fun getItemCount(): Int {
        return artists.size
    }

    override fun onBindViewHolder(holder: ViewHolder, position: Int) {
        holder?.tvArtistType?.text = artists.elementAt(position).name
        holder?.tvArtistFav?.setOnClickListener({ v ->
            run {
                artists.first { a -> a.name == holder?.tvArtistType?.text }.toggleFavorite(holder?.tvArtistFav)
            }
        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        return ViewHolder(LayoutInflater.from(context).inflate(R.layout.item_artist, parent, false))
    }

    fun Artist.toggleFavorite(tv: TextView) {
        if (this.dateFavorite == null ) {
            this.dateFavorite = LocalDateTime.now()
            tv.setText(R.string.remove_from_fav)
        } else {
            this.dateFavorite = null
            tv.setText(R.string.add_to_fav)
        }
    }
}

class ViewHolder (view: View) : RecyclerView.ViewHolder(view) {
    // Holds the TextView that will add each animal to
    val tvArtistType = view.artist_name
    val tvArtistFav = view.fav
}
