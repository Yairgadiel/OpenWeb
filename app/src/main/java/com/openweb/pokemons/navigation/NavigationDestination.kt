package com.openweb.pokemons.navigation

sealed class NavigationDestination<T> {
    class PokemonsScreen<T> : NavigationDestination<T>()
    class PokemonDetailsScreen<T>(val pokemon: T) : NavigationDestination<T>()
}

