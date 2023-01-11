package com.openweb.pokemons.ui.pokemons

import com.openweb.pokemons.models.Pokemon

interface PokemonClickListener {
    fun onClick(pokemon: Pokemon)
}