package nl.stephanmantel.pokemonkmm.shared

import kotlinx.serialization.Serializable

@Serializable
data class ResultDTO<T>(
    val count: Int,
    val next: String?,
    val previous: String?,
    val results: T?
)