package com.hemanth.idrive.data.repositoryImpl


import com.hemanth.idrive.data.RepoService
import com.hemanth.idrive.data.model.QuestionResponse
import com.hemanth.idrive.data.repository.HomeRepository
import io.reactivex.Single
import retrofit2.Response

class HomeRepositoryImpl(
    private val repoService: RepoService
) : HomeRepository {

    override fun getQuestionDetails(): Single<Response<QuestionResponse>> =
        repoService.getQuestionDetails()

}