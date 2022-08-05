package lazycoder21.droid.pull_requests.data.repository

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.flow
import lazycoder21.droid.common.enitity.Resource
import lazycoder21.droid.common.utils.response
import lazycoder21.droid.common.utils.safeApiCall
import lazycoder21.droid.pull_requests.data.remote.GithubPullRequestApi
import lazycoder21.droid.pull_requests.domain.mapper.GithubItemMappers.mapToDomain
import lazycoder21.droid.pull_requests.domain.model.GithubPullRequest
import lazycoder21.droid.pull_requests.domain.repository.GithubRepository
import lazycoder21.droid.pull_requests.utils.constants.GithubPRStatus

internal class GithubRepositoryImpl(
    private val githubPullRequestApi: GithubPullRequestApi,
) : GithubRepository {

    override suspend fun fetchPullRequests(
        userId: String,
        repositoryName: String,
        status: GithubPRStatus,
    ): Flow<Resource<List<GithubPullRequest>?>> = flow {

        safeApiCall(block = {
            val data = githubPullRequestApi.fetchPullRequests(userId, repositoryName, status)
            emit(Resource.Success(data.response()?.mapToDomain))
        }, error = {
            emit(Resource.Error(it))
        })
    }
}