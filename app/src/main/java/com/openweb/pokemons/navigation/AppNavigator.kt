package com.openweb.pokemons.navigation

import com.openweb.pokemons.models.Pokemon

interface AppNavigator {
    fun navigateTo(destination: NavigationDestination<Pokemon>)
}