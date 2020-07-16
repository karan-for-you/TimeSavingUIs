package com.example.myapplication.base

/**
 * Created by karanjeet on 22/10/19
 */
class BasePresenter<V : MvpView> : MvpPresenter<V> {

    var mMvpView : V? = null

    override fun onAttach(view: V) {
        this.mMvpView = view
    }

    override fun onDetach() {
        mMvpView = null
    }

    fun getMvpView() : V?{
        return mMvpView
    }

}