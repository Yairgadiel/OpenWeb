package com.openweb.pokemons.ui.pokemons

import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import androidx.recyclerview.widget.RecyclerView.HORIZONTAL
import com.example.pokemons.R
import com.openweb.pokemons.models.Pokemon
import com.openweb.pokemons.navigation.AppNavigator
import com.openweb.pokemons.navigation.NavigationDestination

class PokemonsFragment : Fragment() {

    companion object {
        fun newInstance() = PokemonsFragment()
    }

    private lateinit var viewModel: PokemonsViewModel

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?,
                              savedInstanceState: Bundle?): View {
        activity?.title = getString(R.string.pokemons_title)
        viewModel = ViewModelProvider(this)[PokemonsViewModel::class.java]
        return inflater.inflate(R.layout.pokemons_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        val pokemonAdapter = PokemonAdapter(object : PokemonClickListener {
            override fun onClick(pokemon: Pokemon) {
                (activity as? AppNavigator)?.navigateTo(NavigationDestination.PokemonDetailsScreen(pokemon))
            }
        })

        val rv: RecyclerView = view.findViewById(R.id.pokemon_list)
        rv.apply {
            adapter = pokemonAdapter
            layoutManager = LinearLayoutManager(context, HORIZONTAL, false)
        }

        viewModel.pokemonRes.observe(viewLifecycleOwner) {
            if (it.isSuccess) {
                pokemonAdapter.submitList(it.getOrNull())
            }
            else {
                Toast.makeText(context, "${it.exceptionOrNull() ?: "Error getting pokemon!"}", Toast.LENGTH_LONG).show()
            }
        }
    }
}