package com.openweb.pokemons.ui.pokemons

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.openweb.pokemons.services.PokemonCallback
import com.openweb.pokemons.services.PokemonsService

class PokemonsViewModel(private val app: Application) : AndroidViewModel(app) {

    private val pokemonService = PokemonsService(app.applicationContext)

    private val _pokemonRes = MutableLiveData<PokemonCallback>()
    val pokemonRes : LiveData<PokemonCallback> = _pokemonRes

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        pokemonService.getPokemons {
           _pokemonRes.postValue(it)
        }
    }
}