package zed.rainxch.githubstore.core.data

import zed.rainxch.githubstore.core.data.services.LocalizationManager
import java.util.Locale

class AndroidLocalizationManager : LocalizationManager {
    override fun getCurrentLanguageCode(): String {
        val locale = Locale.getDefault()
        val language = locale.language
        val country = locale.country
        return if (country.isNotEmpty()) {
            "$language-$country"
        } else {
            language
        }
    }

    override fun getPrimaryLanguageCode(): String {
        return Locale.getDefault().language
    }
}