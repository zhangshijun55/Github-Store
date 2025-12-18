package zed.rainxch.githubstore.feature.settings.presentation

import zed.rainxch.githubstore.core.presentation.model.AppTheme

sealed interface SettingsAction {
    data object OnNavigateBackClick : SettingsAction
    data class OnThemeColorSelected(val themeColor: AppTheme) : SettingsAction
    data class OnAmoledThemeToggled(val enabled: Boolean) : SettingsAction
    data object OnLogoutClick : SettingsAction
    data object OnLogoutConfirmClick : SettingsAction
    data object OnLogoutDismiss : SettingsAction
    data object OnHelpClick : SettingsAction
}