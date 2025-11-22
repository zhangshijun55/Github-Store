package zed.rainxch.githubstore.feature.auth.data

interface TokenStore {
    suspend fun save(token: DeviceTokenSuccess)
    suspend fun load(): DeviceTokenSuccess?
    suspend fun clear()
}

expect fun getGithubClientId(): String

expect object DefaultTokenStore : TokenStore {
    override suspend fun save(token: DeviceTokenSuccess)
    override suspend fun load(): DeviceTokenSuccess?
    override suspend fun clear()
}

expect fun copyToClipboard(label: String, text: String): Boolean
