package com.openweb.pokemons.ui.pokemonDesc

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import com.example.pokemons.R
import com.openweb.pokemons.ui.pokemons.PokemonViewHolder

class PokemonDescFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonDescFragment()
    }

    private val viewModel by activityViewModels<PokemonViewModel>()

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        activity?.title = getString(R.string.pokemon_title)
        return inflater.inflate(R.layout.pokemon_desc_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)


        viewModel.pokemon?.let {
            val desc: TextView = view.findViewById(R.id.pokemon_desc)
            desc.text = it.description

            val pokemonThumbnail: View = view.findViewById(R.id.pokemon)

            PokemonViewHolder(pokemonThumbnail).bind(it)
        } ?: {
            Toast.makeText(context, "Error Displaying Pokemon!", Toast.LENGTH_SHORT).show()
        }
    }
}