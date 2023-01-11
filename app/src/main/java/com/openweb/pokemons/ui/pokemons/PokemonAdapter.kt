package com.openweb.pokemons.ui.pokemons

import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.appcompat.content.res.AppCompatResources
import androidx.core.content.res.ResourcesCompat
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import com.example.pokemons.R
import com.openweb.pokemons.models.Pokemon

class PokemonAdapter(private val onPokemonClickListener: PokemonClickListener) : ListAdapter<Pokemon, PokemonViewHolder>(PokemonDiffUtilCallback) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PokemonViewHolder {
        val view = LayoutInflater.from(parent.context)
            .inflate(R.layout.pokemon_list_item, parent, false)


        return PokemonViewHolder(view, onPokemonClickListener)
    }

    override fun onBindViewHolder(holder: PokemonViewHolder, position: Int) {
        holder.bind(getItem(position))
    }
}

class PokemonViewHolder(itemView: View, private val onPokemonClickListener: PokemonClickListener? = null) : RecyclerView.ViewHolder(itemView) {
    fun bind(pokemon: Pokemon) {
        val resources = itemView.context.resources
        itemView.findViewById<TextView>(R.id.pokemon_name).text = pokemon.name

        var drawableId = resources.getIdentifier(
            "@drawable/" + pokemon.imageFileName() ,
            "pokemon_025.png",
            itemView.context.packageName)

       if (drawableId == 0) {
           drawableId = R.drawable.pokemon_025
       }

        itemView.findViewById<ImageView>(R.id.pokemon_thumbnail)
            .setImageDrawable(ResourcesCompat.getDrawable(resources,
                drawableId, null))

        itemView.setOnClickListener {
            onPokemonClickListener?.onClick(pokemon)
        }
    }
}

object PokemonDiffUtilCallback : DiffUtil.ItemCallback<Pokemon>() {
    override fun areItemsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem.id == newItem.id
    }

    override fun areContentsTheSame(oldItem: Pokemon, newItem: Pokemon): Boolean {
        return oldItem == newItem
    }
}