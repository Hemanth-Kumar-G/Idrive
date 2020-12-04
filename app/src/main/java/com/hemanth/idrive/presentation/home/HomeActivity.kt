package com.hemanth.idrive.presentation.home

import android.content.Intent
import android.content.pm.ActivityInfo
import android.net.Uri
import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AlertDialog
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import com.google.android.material.snackbar.Snackbar
import com.hemanth.idrive.R
import com.hemanth.idrive.base.BaseActivity
import com.hemanth.idrive.common.Constants
import com.hemanth.idrive.databinding.ActivityHomeBinding
import com.hemanth.idrive.presentation.home.adapter.QuestionAdapter
import com.hemanth.idrive.presentation.webUi.WebActivity
import com.hemanth.idrive.utilities.AnswerType
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

/**
 *<h1>HomeActivity</h1>
 * @author : Hemanth     hemanthappu006@gmail.com
 * @version : 1.0
 */
@AndroidEntryPoint
class HomeActivity : BaseActivity() {

    @Inject
    lateinit var adapter: QuestionAdapter

    private lateinit var mBinding: ActivityHomeBinding
    private val viewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestedOrientation = ActivityInfo.SCREEN_ORIENTATION_PORTRAIT
        mBinding = DataBindingUtil.setContentView(this, R.layout.activity_home)
        mBinding.viewModel = viewModel
        init()
        viewModel.getQuestionsList()
        setupObserver()
    }

    private fun init() {
        mBinding.rvQuestionList.adapter = adapter
        adapter.onItemSelectedListener = { answerLink: String, answerType: AnswerType ->
            when (answerType) {
                AnswerType.WEB -> launchWebActivity(answerLink)
                AnswerType.CHROME -> launchChrome(answerLink)
            }
        }
    }

    /**
     * <h2>setupObserver</h2>
     * this method is for observing the response from api
     */
    private fun setupObserver() {
        viewModel.eventList.observe(this, Observer {
            when (it.getContentIfNotPending()?.first) {
                Constants.SUCCESS -> notifyAdapter()
                Constants.ERROR -> showError(it.getContent().second.toString())
                Constants.NO_INTERNET -> showInternetError()
            }
        })
    }

    private fun showInternetError() {
        AlertDialog.Builder(this)
            .setMessage(getString(R.string.no_internet_error))
            .setPositiveButton(getString(R.string.retry)) { _, _ -> viewModel.getQuestionsList() }
            .setOnCancelListener { viewModel.getQuestionsList() }
            .show()
    }

    private fun showError(error: String) {
        val snackBar: Snackbar = Snackbar.make(mBinding.root, error, Snackbar.LENGTH_SHORT)
        snackBar.view.setBackgroundColor(
            ContextCompat.getColor(this, R.color.snackbar_error_background_color)
        )
        snackBar.show()
    }

    /**
     * <h2>notifyAdapter</h2>
     * this is method for notifying  adapter
     */
    private fun notifyAdapter() {
        adapter.notifyDataSetChanged()
    }

    private fun launchChrome(answerLink: String) {
        startActivity(Intent(Intent.ACTION_VIEW, Uri.parse(answerLink)))
    }

    private fun launchWebActivity(answerLink: String) {
        startActivity(Intent(this, WebActivity::class.java).apply {
            putExtra(Constants.ANSWER_LINK, answerLink)
        })
    }

}