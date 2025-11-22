package zed.rainxch.githubstore.feature.auth.domain

import kotlinx.coroutines.flow.Flow
import zed.rainxch.githubstore.feature.auth.data.DeviceStart
import zed.rainxch.githubstore.feature.auth.data.DeviceTokenSuccess
import zed.rainxch.githubstore.feature.auth.domain.repository.AuthRepository

class StartDeviceFlowUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(scope: String): DeviceStart = repo.startDeviceFlow(scope)
}

class AwaitDeviceTokenUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke(start: DeviceStart): DeviceTokenSuccess = repo.awaitDeviceToken(start)
}

class ObserveAccessTokenUseCase(private val repo: AuthRepository) {
    operator fun invoke(): Flow<String?> = repo.accessTokenFlow
}

class LogoutUseCase(private val repo: AuthRepository) {
    suspend operator fun invoke() = repo.logout()
}
