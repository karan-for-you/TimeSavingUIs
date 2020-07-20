package com.example.myapplication.searchreveal

import android.animation.Animator
import android.os.Bundle
import android.view.View
import android.view.ViewAnimationUtils
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivitySearchBinding
import com.example.myapplication.utils.Logger

class SearchActivity : BaseActivity() {

    lateinit var bindingSearchActivity: ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSearchActivity = DataBindingUtil.setContentView(
            this, R.layout.activity_search
        )
        bindingSearchActivity.ivOpenSearch.setOnClickListener { openSearch() }

        bindingSearchActivity.ivCloseSearch.setOnClickListener { closeSearch() }

        bindingSearchActivity.ivStartSearch.setOnClickListener {
            showToast(bindingSearchActivity.etSearchText.text.toString())
        }

    }

    private fun openSearch() {
        bindingSearchActivity.etSearchText.setText("")
        bindingSearchActivity.rlSearchOpened.visibility = View.VISIBLE
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            bindingSearchActivity.rlSearchOpened,
            (bindingSearchActivity.ivOpenSearch.right + bindingSearchActivity.ivOpenSearch.left) / 2,
            (bindingSearchActivity.ivOpenSearch.top + bindingSearchActivity.ivOpenSearch.bottom) / 2,
            0f, bindingSearchActivity.flSearchBar.width.toFloat() - 100
        )
        circularReveal.duration = 300
        circularReveal.start()
    }

    private fun closeSearch() {
        val circularConceal = ViewAnimationUtils.createCircularReveal(
            bindingSearchActivity.rlSearchOpened,
            (bindingSearchActivity.ivOpenSearch.right + bindingSearchActivity.ivOpenSearch.left) / 2,
            (bindingSearchActivity.ivOpenSearch.top + bindingSearchActivity.ivOpenSearch.bottom) / 2,
            bindingSearchActivity.flSearchBar.width.toFloat(), 0f
        )

        circularConceal.duration = 300
        circularConceal.start()
        circularConceal.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) = Unit
            override fun onAnimationEnd(animation: Animator?) {
                bindingSearchActivity.rlSearchOpened.visibility = View.INVISIBLE
                bindingSearchActivity.etSearchText.setText("")
                circularConceal.removeAllListeners()
            }
        })
    }

    override fun onBackPressed() {

    }

}