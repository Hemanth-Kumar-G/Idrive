package com.hemanth.idrive.presentation.home


import com.hemanth.idrive.data.model.QuestionResponse
import com.hemanth.idrive.presentation.home.adapter.QuestionAdapter
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.components.ActivityComponent
import dagger.hilt.android.scopes.ActivityScoped

/**
 *<h1>HomeModule</h1>
 * <p>dagger module class</p>
 * @author : Hemanth     hemanthappu006@gmail.com
 * @version : 1.0
 */
@Module
@InstallIn(ActivityComponent::class)
class HomeModule {

    @Provides
    @ActivityScoped
    fun provideList() = ArrayList<QuestionResponse.Tree.Item>()

    @Provides
    @ActivityScoped
    fun provideQuestionAdapter(list:ArrayList<QuestionResponse.Tree.Item>)=QuestionAdapter(list)
}