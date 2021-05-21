package nl.stephanmantel.pokemonkmm.androidApp

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import nl.stephanmantel.pokemonkmm.shared.Pokemon
import nl.stephanmantel.pokemonkmm.shared.PokemonRepository

class MainViewModel(
    private val pokemonRepository: PokemonRepository
) : ViewModel() {

    private val _latestPokemon = MutableLiveData<Pokemon>()
    internal val latestPokemon: LiveData<Pokemon> get() = _latestPokemon

    init {
        loadPokemon()
    }

    private fun loadPokemon() {
        viewModelScope.launch(Dispatchers.Main) {
            pokemonRepository.getNetworkPokemonContinuous()
                .collect {
                    _latestPokemon.value = it
                }
        }
    }
}