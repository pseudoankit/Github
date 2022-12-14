package lazycoder21.droid.pull_requests.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.common.utils.data
import lazycoder21.droid.common.utils.safeApiCall
import lazycoder21.droid.pull_requests.data.remote.PullRequestApi
import lazycoder21.droid.pull_requests.data.remote.dto.PullRequestDto
import lazycoder21.droid.pull_requests.domain.api_param_model.PullRequestParam
import lazycoder21.droid.pull_requests.domain.mapper.PullRequestMapper.mapToDomain
import lazycoder21.droid.pull_requests.domain.model.PullRequest
import lazycoder21.droid.pull_requests.domain.repository.IPullRequestRepository
import retrofit2.Response
import javax.inject.Inject

class PullRequestRepository @Inject constructor(
    private val pullRequestApi: PullRequestApi,
) : IPullRequestRepository {

    override suspend fun fetchPullRequests(
        param: PullRequestParam
    ): Flow<Resource<List<PullRequest>>> = flow {

        emit(Resource.Loading(isLoading = true))

        safeApiCall(block = {
            val response: Response<List<PullRequestDto>> = param.run {
                pullRequestApi.fetchPullRequests(
                    userId, repoName, status.status, perPage, pageNo
                )
            }

            emit(response.data { it.mapToDomain })
        }, error = {
            emit(Resource.Error(it))
        })

        emit(Resource.Loading(isLoading = false))
    }
}