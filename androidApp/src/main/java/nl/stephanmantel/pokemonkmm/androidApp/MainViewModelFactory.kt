package nl.stephanmantel.pokemonkmm.androidApp

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import nl.stephanmantel.pokemonkmm.shared.PokemonRepositoryImpl

class MainViewModelFactory : ViewModelProvider.Factory {

    private val repo by lazy {
        PokemonRepositoryImpl()
    }

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {
        return modelClass.cast(
            MainViewModel(repo)
        ) as T
    }

}