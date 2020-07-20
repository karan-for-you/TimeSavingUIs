package com.example.myapplication.searchreveal

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivitySearchBinding

class SearchActivity : BaseActivity() {

    lateinit var bindingSearchActivity :ActivitySearchBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSearchActivity = DataBindingUtil.setContentView(
            this, R.layout.activity_search)
        bindingSearchActivity.svToolbar.bindingViewSearch.executeSearchButton.setOnClickListener {
            showToast(bindingSearchActivity.svToolbar.bindingViewSearch.searchInputText.text.toString())
        }
    }

    override fun onBackPressed() {
        if(bindingSearchActivity.svToolbar.bindingViewSearch.searchOpenView.isVisible)
            bindingSearchActivity.svToolbar.bindingViewSearch.closeSearchButton.performClick()
        else
            finish()
        super.onBackPressed()
    }

}