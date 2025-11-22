package zed.rainxch.githubstore.app.di

import org.koin.core.module.Module
import org.koin.dsl.module
import zed.rainxch.githubstore.core.data.DefaultTokenDataSource
import zed.rainxch.githubstore.core.data.TokenDataSource
import zed.rainxch.githubstore.feature.auth.data.repository.AuthRepositoryImpl
import zed.rainxch.githubstore.feature.auth.domain.*
import zed.rainxch.githubstore.feature.auth.domain.repository.AuthRepository
import zed.rainxch.githubstore.feature.auth.presentation.AuthenticationViewModel

// Core/shared modules
val coreModule: Module = module {
    single<TokenDataSource> { DefaultTokenDataSource() }
}

val authModule: Module = module {
    single<AuthRepository> { AuthRepositoryImpl(tokenDataSource = get()) }

    // Use cases
    factory { StartDeviceFlowUseCase(get()) }
    factory { AwaitDeviceTokenUseCase(get()) }
    factory { ObserveAccessTokenUseCase(get()) }
    factory { LogoutUseCase(get()) }

    // Presentation
    factory { AuthenticationViewModel(get(), get(), get(), get()) }
}
