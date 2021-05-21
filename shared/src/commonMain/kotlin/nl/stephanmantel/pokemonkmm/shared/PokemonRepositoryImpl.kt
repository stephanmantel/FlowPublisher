package nl.stephanmantel.pokemonkmm.shared

import io.ktor.client.*
import io.ktor.client.features.json.*
import io.ktor.client.features.json.serializer.*
import io.ktor.client.request.*
import kotlinx.coroutines.currentCoroutineContext
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import kotlinx.coroutines.isActive

class PokemonRepositoryImpl : PokemonRepository {

    override suspend fun getPokemon(): Pokemon {
        return Pokemon(
            "Blastoise",
            ""
        )
    }

    override fun getLocalPokemonContinuous(): Flow<Pokemon> {
        return flow {
            while (currentCoroutineContext().isActive) {
                emit(Pokemon("Charizard", ""))
                delay(1000)
                emit(Pokemon("Seal", ""))
                delay(1000)
                emit(Pokemon("Raichu", ""))
                delay(1000)

            }
        }
    }

    override fun getNetworkPokemon(): Flow<Pokemon> {
        val client = HttpClient(httpEngineFactory) {
            install(JsonFeature) {
                serializer = KotlinxSerializer()
            }
        }
        return flow {
            val pokemonResult: ResultDTO<List<Pokemon>> = client.get("https://pokeapi.co/api/v2/pokemon?limit=151")
            pokemonResult.results?.forEach {
                emit(it)
                delay(1000)
            }
        }
    }
}