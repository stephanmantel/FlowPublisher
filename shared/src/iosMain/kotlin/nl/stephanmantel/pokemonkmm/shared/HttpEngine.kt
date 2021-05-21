package nl.stephanmantel.pokemonkmm.shared

import io.ktor.client.engine.*
import io.ktor.client.engine.ios.*

actual val httpEngineFactory : HttpClientEngineFactory<*> = Ios