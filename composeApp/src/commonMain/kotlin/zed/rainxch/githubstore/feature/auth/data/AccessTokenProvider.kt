package zed.rainxch.githubstore.feature.auth.data

import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow

/**
 * Holds the latest OAuth access token in-memory for easy access across the app.
 *
 * - `current()` returns a snapshot string (nullable) suitable for headers.
 * - `flow` can be observed if you need reactive updates.
 *
 * AuthCoordinator is responsible for updating this provider on login/logout/startup.
 */
object AccessTokenProvider {
    private val _flow = MutableStateFlow<String?>(null)
    val flow: StateFlow<String?> = _flow.asStateFlow()

    fun current(): String? = _flow.value

    fun setToken(token: String?) {
        _flow.value = token
    }

    fun clear() { _flow.value = null }
}
