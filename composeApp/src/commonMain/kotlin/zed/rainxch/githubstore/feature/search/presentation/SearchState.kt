package zed.rainxch.githubstore.feature.search.presentation

import zed.rainxch.githubstore.core.domain.model.GithubRepoSummary
import zed.rainxch.githubstore.feature.search.domain.model.SearchPlatformType
import zed.rainxch.githubstore.feature.search.domain.model.SortBy

data class SearchState(
    val search: String = "",
    val selectedSortBy: SortBy = SortBy.BestMatch,
    val selectedSearchPlatformType: SearchPlatformType = SearchPlatformType.All,
    val repositories: List<SearchRepo> = emptyList(),
    val totalCount: Int? = null,
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val hasMorePages: Boolean = true,
    val errorMessage: String? = null
)

data class SearchRepo(
    val isInstalled: Boolean,
    val isUpdateAvailable: Boolean,
    val repo: GithubRepoSummary
)
