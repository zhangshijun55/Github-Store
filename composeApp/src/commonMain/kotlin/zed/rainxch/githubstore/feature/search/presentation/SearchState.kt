package zed.rainxch.githubstore.feature.search.presentation

import zed.rainxch.githubstore.core.presentation.model.DiscoveryRepository
import zed.rainxch.githubstore.feature.search.domain.model.ProgrammingLanguage
import zed.rainxch.githubstore.feature.search.domain.model.SearchPlatformType
import zed.rainxch.githubstore.feature.search.domain.model.SortBy

data class SearchState(
    val query: String = "",
    val repositories: List<DiscoveryRepository> = emptyList(),
    val selectedSearchPlatformType: SearchPlatformType = SearchPlatformType.All,
    val selectedSortBy: SortBy = SortBy.BestMatch,
    val selectedLanguage: ProgrammingLanguage = ProgrammingLanguage.All,
    val isLoading: Boolean = false,
    val isLoadingMore: Boolean = false,
    val errorMessage: String? = null,
    val hasMorePages: Boolean = true,
    val totalCount: Int? = null,
    val isLanguageSheetVisible: Boolean = false
)
