package com.openweb.pokemons.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Toast
import androidx.activity.viewModels
import androidx.fragment.app.Fragment
import androidx.fragment.app.commit
import com.example.pokemons.R
import com.openweb.pokemons.models.Pokemon
import com.openweb.pokemons.navigation.AppNavigator
import com.openweb.pokemons.navigation.NavigationDestination
import com.openweb.pokemons.ui.pokemonDesc.PokemonDescFragment
import com.openweb.pokemons.ui.pokemonDesc.PokemonViewModel
import com.openweb.pokemons.ui.pokemons.PokemonsFragment
import com.openweb.pokemons.ui.welcome.WelcomeFragment

class MainActivity : AppCompatActivity(), AppNavigator {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.welcome_activity)
        if (savedInstanceState == null) {
            navigateTo(WelcomeFragment.newInstance())
        }
    }

    override fun navigateTo(destination: NavigationDestination<Pokemon>) {
        val destinationFragment = when (destination) {
            is NavigationDestination.PokemonsScreen<*> -> PokemonsFragment.newInstance()
            is NavigationDestination.PokemonDetailsScreen<Pokemon> -> {
                val pokemonViewModel by viewModels<PokemonViewModel>()
                pokemonViewModel.pokemon = destination.pokemon
                PokemonDescFragment.newInstance()
            }
            else -> null
        }

        if (destinationFragment == null) {
            Toast.makeText(this, "App navigation error!", Toast.LENGTH_SHORT).show()
        }
        else {
            navigateTo(destinationFragment)
        }
    }

    private fun navigateTo(fragment: Fragment) {
        // FIXME: No backstack
        supportFragmentManager.beginTransaction().apply {
            add(R.id.container, fragment, null)
            addToBackStack("frag")
            commit()
        }
    }
}