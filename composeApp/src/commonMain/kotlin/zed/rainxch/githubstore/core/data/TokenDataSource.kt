package zed.rainxch.githubstore.core.data

import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import zed.rainxch.githubstore.feature.auth.data.DeviceTokenSuccess
import zed.rainxch.githubstore.feature.auth.data.DefaultTokenStore

/**
 * TokenDataSource is the source of truth for auth tokens.
 * It persists tokens and exposes a StateFlow so presentation/data can react without
 * relying on in-memory singletons. The backing store is platform-specific.
 */
interface TokenDataSource {
    val tokenFlow: StateFlow<DeviceTokenSuccess?>
    suspend fun save(token: DeviceTokenSuccess)
    suspend fun load(): DeviceTokenSuccess?
    suspend fun clear()
}

/**
 * Default implementation that delegates to existing platform DefaultTokenStore (persisted),
 * and mirrors updates into an in-memory StateFlow sourced from persistence at startup.
 */
class DefaultTokenDataSource(
    private val scope: CoroutineScope = CoroutineScope(Dispatchers.Default)
) : TokenDataSource {
    private val _flow = MutableStateFlow<DeviceTokenSuccess?>(null)
    override val tokenFlow: StateFlow<DeviceTokenSuccess?> = _flow

    init {
        scope.launch {
            _flow.value = DefaultTokenStore.load()
        }
    }

    override suspend fun save(token: DeviceTokenSuccess) {
        DefaultTokenStore.save(token)
        _flow.value = token
    }

    override suspend fun load(): DeviceTokenSuccess? = DefaultTokenStore.load()

    override suspend fun clear() {
        DefaultTokenStore.clear()
        _flow.value = null
    }
}
