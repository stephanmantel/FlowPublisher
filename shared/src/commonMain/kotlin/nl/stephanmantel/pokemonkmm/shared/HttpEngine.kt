package nl.stephanmantel.pokemonkmm.shared

import io.ktor.client.engine.*

expect val httpEngineFactory : HttpClientEngineFactory<*>