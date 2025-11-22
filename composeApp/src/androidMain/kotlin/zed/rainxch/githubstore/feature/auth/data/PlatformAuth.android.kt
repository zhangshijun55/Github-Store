package zed.rainxch.githubstore.feature.auth.data

import android.content.ClipData
import android.content.ClipboardManager
import android.content.Context
import androidx.security.crypto.EncryptedSharedPreferences
import androidx.security.crypto.MasterKey
import androidx.security.crypto.MasterKey.*
import zed.rainxch.githubstore.BuildConfig
import kotlinx.serialization.json.Json
import zed.rainxch.githubstore.core.presentation.utils.AppContextHolder

actual fun getGithubClientId(): String = BuildConfig.GITHUB_CLIENT_ID

actual fun copyToClipboard(label: String, text: String): Boolean {
    return try {
        val ctx: Context = AppContextHolder.appContext
        val cm = ctx.getSystemService(Context.CLIPBOARD_SERVICE) as ClipboardManager
        cm.setPrimaryClip(ClipData.newPlainText(label, text))
        true
    } catch (_: Throwable) { false }
}

actual object DefaultTokenStore : TokenStore {
    private val ctx: Context get() = AppContextHolder.appContext

    private val prefs by lazy {
        try {
            val masterKey = Builder(ctx)
                .setKeyScheme(KeyScheme.AES256_GCM)
                .build()
            EncryptedSharedPreferences.create(
                ctx,
                "auth_tokens",
                masterKey,
                EncryptedSharedPreferences.PrefKeyEncryptionScheme.AES256_SIV,
                EncryptedSharedPreferences.PrefValueEncryptionScheme.AES256_GCM
            )
        } catch (_: Throwable) {
            ctx.getSharedPreferences("auth_tokens", Context.MODE_PRIVATE)
        }
    }

    private val json = Json { ignoreUnknownKeys = true }

    actual override suspend fun save(token: DeviceTokenSuccess) {
        prefs.edit().putString("token", json.encodeToString(DeviceTokenSuccess.serializer(), token)).apply()
    }

    actual override suspend fun load(): DeviceTokenSuccess? {
        val raw = prefs.getString("token", null) ?: return null
        return runCatching { json.decodeFromString(DeviceTokenSuccess.serializer(), raw) }.getOrNull()
    }

    actual override suspend fun clear() {
        prefs.edit().remove("token").apply()
    }
}
