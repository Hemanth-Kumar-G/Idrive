package com.hemanth.idrive.data.repository


import com.hemanth.idrive.data.model.QuestionResponse
import io.reactivex.Single
import retrofit2.Response

interface HomeRepository {

    fun getQuestionDetails(): Single<Response<QuestionResponse>>

}