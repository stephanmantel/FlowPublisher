package nl.stephanmantel.pokemonkmm.shared

import kotlinx.coroutines.flow.Flow

interface PokemonRepository {

    suspend fun getPokemon(): Pokemon

    fun getLocalPokemonContinuous(): Flow<Pokemon>

    fun getNetworkPokemon(): Flow<Pokemon>
}
