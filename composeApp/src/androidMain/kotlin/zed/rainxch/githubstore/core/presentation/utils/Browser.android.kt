package zed.rainxch.githubstore.core.presentation.utils

import android.content.Intent
import android.net.Uri
import android.content.Context

object AppContextHolder {
    lateinit var appContext: Context
}

actual fun openBrowser(url: String) {
    val ctx = AppContextHolder.appContext
    val intent = Intent(Intent.ACTION_VIEW, Uri.parse(url)).apply {
        addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)
    }
    ctx.startActivity(intent)
}
