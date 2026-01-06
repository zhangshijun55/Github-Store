package zed.rainxch.githubstore.feature.home.presentation

import zed.rainxch.githubstore.core.data.local.db.entities.InstalledApp
import zed.rainxch.githubstore.core.domain.model.GithubRepoSummary
import zed.rainxch.githubstore.core.presentation.model.DiscoveryRepository
import zed.rainxch.githubstore.feature.home.presentation.model.HomeCategory

data class HomeState(
    val repos: List<DiscoveryRepository> = emptyList(),
    val installedApps: List<InstalledApp> = emptyList(),
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val errorMessage: String? = null,
    val hasMorePages: Boolean = true,
    val currentCategory: HomeCategory = HomeCategory.TRENDING,
    val isAppsSectionVisible: Boolean = false,
    val isUpdateAvailable: Boolean = false,
)