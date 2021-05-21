package nl.stephanmantel.pokemonkmm.shared.utils

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.flow.*

class KotlinNativeFlowWrapper<T>(
    private val flow: Flow<T>
) {
    fun subscribe(
        scope: CoroutineScope,
        onEach: (item: T) -> Unit,
        onComplete: () -> Unit,
        onThrow: (error: Throwable) -> Unit
    ) = flow
        .onEach { onEach(it) }
        .catch { onThrow(it) }
        .onCompletion { onComplete() }
        .launchIn(scope)
}