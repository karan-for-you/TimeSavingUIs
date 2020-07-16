package com.example.myapplication.base

import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import com.example.myapplication.utils.Network
import com.example.myapplication.utils.UI

/**
 * Created by karanjeet on 22/10/19
 */
abstract class BaseActivity : AppCompatActivity(), MvpView {

    private var progressBar : AlertDialog? = null

    override fun showToast(message : String) {
        UI.showToast(message,this)?.show()
    }

    override fun showToast(resId: Int) {
        UI.showToast(resId,this)?.show()
    }

    override fun showLongToast(message: String) {
        UI.showLongToast(message,this)?.show()
    }

    override fun showLongToast(resId: Int) {
        UI.showLongToast(resId,this)?.show()
    }

    override fun showProgressBar() {
        progressBar = UI.showProgressBar(this)
        progressBar!!.show()
    }

    override fun showProgressBar(message : String) {
        progressBar = UI.showProgressBar(this,message)
        progressBar!!.show()
    }

    override fun showProgressBar(resId: Int) {
        progressBar = UI.showProgressBar(this,resId)
        progressBar!!.show()
    }

    override fun hideProgressBar() {
        if(progressBar!=null && progressBar!!.isShowing){
            progressBar!!.cancel()
        }
    }

    override fun isNetworkConnected():Boolean {
        return Network.isNetworkAvailable(this)
    }

    override fun showKeyboard() {
        UI.showSoftKeyboard(this)
    }

    override fun hideKeyboard() {
        UI.hideSoftKeyboard(this)
    }

}