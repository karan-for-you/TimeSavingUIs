package com.example.myapplication.searchreveal

import android.animation.Animator
import android.content.Context
import android.util.AttributeSet
import android.view.LayoutInflater
import android.view.View
import android.view.ViewAnimationUtils
import android.widget.FrameLayout
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.databinding.ViewSerachBinding

class SearchView(
    context: Context,
    attrs: AttributeSet
) : FrameLayout(context, attrs) {

    var bindingViewSearch : ViewSerachBinding = DataBindingUtil.inflate(LayoutInflater.from(context),
        R.layout.view_serach, this, true)

    init {
        bindingViewSearch.openSearchButton.setOnClickListener { openSearch() }
        bindingViewSearch.closeSearchButton.setOnClickListener { closeSearch() }
    }

    private fun openSearch() {
        bindingViewSearch.searchInputText.setText("")
        bindingViewSearch.searchOpenView.visibility = View.VISIBLE
        bindingViewSearch.searchOpenView.visibility = View.VISIBLE
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            bindingViewSearch.openSearchButton,
            (bindingViewSearch.searchOpenView.right + bindingViewSearch.searchOpenView.left) / 2,
            (bindingViewSearch.searchOpenView.top + bindingViewSearch.searchOpenView.bottom) / 2,
            0f, width.toFloat()
        )
        circularReveal.duration = 700
        circularReveal.start()
    }

    private fun closeSearch() {
        val circularConceal = ViewAnimationUtils.createCircularReveal(
            bindingViewSearch.openSearchButton,
            (bindingViewSearch.searchOpenView.right + bindingViewSearch.searchOpenView.left) / 2,
            (bindingViewSearch.searchOpenView.top + bindingViewSearch.searchOpenView.bottom) / 2,
            width.toFloat(), 0f
        )

        circularConceal.duration = 700
        circularConceal.start()
        circularConceal.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) = Unit
            override fun onAnimationEnd(animation: Animator?) {
                bindingViewSearch.searchOpenView.visibility = View.INVISIBLE
                bindingViewSearch.searchInputText.setText("")
                circularConceal.removeAllListeners()
            }
        })
    }

}