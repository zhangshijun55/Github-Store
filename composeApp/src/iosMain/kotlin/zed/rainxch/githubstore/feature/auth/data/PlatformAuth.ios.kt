package zed.rainxch.githubstore.feature.auth.data

import kotlinx.serialization.json.Json
import platform.Foundation.NSUserDefaults
import platform.Foundation.NSProcessInfo
import platform.UIKit.UIPasteboard

actual fun getGithubClientId(): String {
    val env = NSProcessInfo.processInfo.environment
    val key = "GITHUB_CLIENT_ID"
    val value = env?.get(key) as? String
    return value ?: ""
}

actual fun copyToClipboard(label: String, text: String): Boolean {
    return try {
        UIPasteboard.generalPasteboard.setString(text)
        true
    } catch (_: Throwable) { false }
}

actual object DefaultTokenStore : TokenStore {
    private val defaults = NSUserDefaults.standardUserDefaults()
    private val json = Json { ignoreUnknownKeys = true }

    actual override suspend fun save(token: DeviceTokenSuccess) {
        val raw = json.encodeToString(DeviceTokenSuccess.serializer(), token)
        defaults.setObject(raw, forKey = "token")
        defaults.synchronize()
    }

    actual override suspend fun load(): DeviceTokenSuccess? {
        val raw = defaults.stringForKey("token") ?: return null
        return runCatching { json.decodeFromString(DeviceTokenSuccess.serializer(), raw) }.getOrNull()
    }

    actual override suspend fun clear() {
        defaults.removeObjectForKey("token")
        defaults.synchronize()
    }
}
