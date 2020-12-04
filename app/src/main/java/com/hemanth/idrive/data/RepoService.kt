package com.hemanth.idrive.data


import com.hemanth.idrive.data.model.QuestionResponse
import io.reactivex.Single
import retrofit2.Response
import retrofit2.http.GET

interface RepoService {

    @GET("/android-help/android-faq-list.txt")
    fun getQuestionDetails(): Single<Response<QuestionResponse>>

}