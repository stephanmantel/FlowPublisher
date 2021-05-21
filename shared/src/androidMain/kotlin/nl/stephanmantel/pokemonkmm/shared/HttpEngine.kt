package nl.stephanmantel.pokemonkmm.shared

import io.ktor.client.engine.*
import io.ktor.client.engine.cio.*

actual val httpEngineFactory : HttpClientEngineFactory<*> = CIO