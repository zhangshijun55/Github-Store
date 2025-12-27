package zed.rainxch.githubstore.core.data

import zed.rainxch.githubstore.core.data.services.LocalizationManager

class DesktopLocalizationManager : LocalizationManager {
    override fun getCurrentLanguageCode(): String {
        val locale = java.util.Locale.getDefault()
        val language = locale.language
        val country = locale.country
        return if (country.isNotEmpty()) {
            "$language-$country"
        } else {
            language
        }
    }
    
    override fun getPrimaryLanguageCode(): String {
        return java.util.Locale.getDefault().language
    }
}