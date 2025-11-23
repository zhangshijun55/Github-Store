package zed.rainxch.githubstore.app.navigation

import kotlinx.serialization.Serializable

sealed interface GithubStoreGraph {
    @Serializable
    data object HomeScreen : GithubStoreGraph

    @Serializable
    data object AuthenticationScreen : GithubStoreGraph
}