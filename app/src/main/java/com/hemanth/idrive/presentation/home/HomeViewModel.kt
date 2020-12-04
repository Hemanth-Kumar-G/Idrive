package com.hemanth.idrive.presentation.home

import androidx.databinding.ObservableBoolean
import androidx.hilt.lifecycle.ViewModelInject
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.hemanth.idrive.base.BaseViewModel
import com.hemanth.idrive.common.Constants
import com.hemanth.idrive.common.NoConnectivityException
import com.hemanth.idrive.data.model.QuestionResponse
import com.hemanth.idrive.data.repository.HomeRepository
import com.hemanth.idrive.utilities.Event
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.observers.DisposableSingleObserver
import io.reactivex.schedulers.Schedulers
import retrofit2.Response

/**
 *<h1>HomeViewModel</h1>
 * @author : Hemanth     hemanthappu006@gmail.com
 * @version : 1.0
 */
class HomeViewModel @ViewModelInject constructor(
    private val homeRepository: HomeRepository,
    private val questionList: ArrayList<QuestionResponse.Tree.Item>
) : BaseViewModel() {

    val loading = ObservableBoolean(true)

    private val _eventList = MutableLiveData<Event<Pair<String, *>>>()
    val eventList: LiveData<Event<Pair<String, *>>> = _eventList

    fun getQuestionsList() {
        setLoading(true)
        val disposableObserver =
            object : DisposableSingleObserver<Response<QuestionResponse>>() {
                override fun onSuccess(value: Response<QuestionResponse>) {
                    when (value.code()) {
                        200 -> {
                            saveResponse(value.body() as QuestionResponse)
                            _eventList.postValue(
                                Event(Pair(Constants.SUCCESS, value.body()))
                            )
                        }
                        else -> _eventList.postValue(
                            Event(Pair(Constants.ERROR, value.code().toString()))
                        )
                    }
                    setLoading(false)
                }

                override fun onError(e: Throwable) {
                    setLoading(false)
                    if (e is NoConnectivityException)
                        _eventList.postValue(Event(Pair(Constants.NO_INTERNET, e.message)))
                    else
                        _eventList.postValue(Event(Pair(Constants.ERROR, e.message)))
                }
            }
        homeRepository.getQuestionDetails().subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(disposableObserver)
    }

    private fun saveResponse(questionResponse: QuestionResponse?) {
        questionList.clear()
        questionResponse?.let { questionList.addAll(it.tree.item) }
    }

    fun setLoading(isLoading: Boolean) {
        loading.set(isLoading)
    }

    fun onRefresh() {
        getQuestionsList()
    }

}