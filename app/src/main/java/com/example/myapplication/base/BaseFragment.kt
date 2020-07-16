package com.example.myapplication.base

import android.content.Context
import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment

/**
 * Created by karanjeet on 22/10/19
 */
abstract class BaseFragment : Fragment(), MvpView {
    private var baseActivity : BaseActivity? = null

    override fun onAttach(context: Context) {
        super.onAttach(context)
        if(context is BaseActivity) {
            baseActivity = context
        }
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupViews(view)
    }

    abstract fun setupViews(view : View)

    override fun showToast(message: String) {
        if(baseActivity!=null){
            baseActivity!!.showToast(message)
        }
    }

    override fun showLongToast(message: String) {
        if(baseActivity!=null){
            baseActivity!!.showLongToast(message)
        }
    }

    override fun showToast(resId: Int) {
        if(baseActivity!=null){
            baseActivity!!.showToast(resId)
        }
    }

    override fun showLongToast(resId: Int) {
        if(baseActivity!=null){
            baseActivity!!.showLongToast(resId)
        }
    }

    override fun showProgressBar() {
        if(baseActivity!=null){
            baseActivity!!.showProgressBar()
        }
    }

    override fun showProgressBar(message: String) {
        if(baseActivity!=null){
            baseActivity!!.showProgressBar(message)
        }
    }

    override fun showProgressBar(resId: Int) {
        if(baseActivity!=null){
            baseActivity!!.showProgressBar(resId)
        }
    }

    override fun hideProgressBar() {
        if(baseActivity!=null){
            baseActivity!!.hideProgressBar()
        }
    }

    override fun isNetworkConnected():Boolean {
        return baseActivity!=null && baseActivity!!.isNetworkConnected()
    }

    override fun showKeyboard() {
        if(baseActivity!=null){
            baseActivity!!.showKeyboard()
        }
    }

    override fun hideKeyboard() {
        if(baseActivity!=null){
            baseActivity!!.hideKeyboard()
        }
    }

}