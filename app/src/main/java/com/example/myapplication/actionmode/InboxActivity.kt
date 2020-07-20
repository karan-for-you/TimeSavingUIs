package com.example.myapplication.actionmode

import android.animation.Animator
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.ViewAnimationUtils
import androidx.appcompat.view.ActionMode
import androidx.core.view.isVisible
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityInboxBinding
import com.example.myapplication.utils.Logger
import java.util.*

class InboxActivity : BaseActivity(),  InboxAdapter.OnClickListeners {

    private lateinit var bindingInboxBinding : ActivityInboxBinding
    private lateinit var inboxViewModel : InboxViewModel
    private var inboxList = ArrayList<Inbox>()
    private var inboxAdapter : InboxAdapter? = null
    private var actionMode : ActionMode? = null
    private var actionModeCallback : ActionModeCallBack? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInboxBinding = DataBindingUtil.setContentView(this,R.layout.activity_inbox)
        prepareActionModeCallback()
        setupViewModelAndHandler()
        setupInboxRecyclerView()
        inboxViewModel.prepareLiveData()
        inboxViewModel.getInboxLiveData().observe(this,
            Observer<List<Inbox>> { t -> inboxAdapter?.addData(t!!) })
        bindingInboxBinding.ivOpenSearch.setOnClickListener { openSearch() }
        bindingInboxBinding.ivCloseSearch.setOnClickListener { closeSearch() }

        bindingInboxBinding.etSearchText.addTextChangedListener(object : TextWatcher{
            override fun afterTextChanged(p0: Editable?) {}
            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {}
            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                inboxAdapter?.getFilter()?.filter(p0)
            }

        })
    }

    private fun prepareActionModeCallback(){
        actionModeCallback = ActionModeCallBack(this)
    }

    private fun setupViewModelAndHandler(){
        inboxViewModel = ViewModelProvider(this).get(InboxViewModel::class.java)
        bindingInboxBinding.viewModel = inboxViewModel
    }

    private fun setupInboxRecyclerView(){
        inboxAdapter = InboxAdapter(inboxList,
            inboxList,
            context = this,
            inboxClickListener = this,
            inboxActivity = this)
        bindingInboxBinding.recyclerViewInbox.apply {
            layoutManager = LinearLayoutManager(this@InboxActivity)
            adapter = inboxAdapter
        }
    }

    override fun onBackPressed() {
        Logger.logError("Going in ","Back Pressed")
        when {
            bindingInboxBinding.rlSearchOpened.isVisible ->
                bindingInboxBinding.ivCloseSearch.performClick()
            else -> finish()
        }
    }

    override fun onClicked(inbox: Inbox, position : Int, holder: InboxAdapter.InboxViewHolder) {
        if(actionMode!=null)
            inboxAdapter?.selectInboxMessages(holder, position)
        else showToast(inbox.senderName)
    }

    override fun onLongClicked(inbox: Inbox, position : Int, holder: InboxAdapter.InboxViewHolder) {
        if(!bindingInboxBinding.rlSearchOpened.isVisible) {
            if (actionMode == null) {
                actionMode = startSupportActionMode(actionModeCallback!!)
                inboxAdapter?.selectInboxMessages(holder, position)
            } else inboxAdapter?.selectInboxMessages(holder, position)
        }
    }

    // Upon selection of Back icon on action mode toolbar
    fun clearMessagesAndResetUI(){
        inboxAdapter?.setUnselected()
        inboxAdapter?.selectedInboxMessages?.clear() // Clear previous Data
        if(actionMode!=null) {
            actionMode!!.finish()
            actionMode = null
        }
    }

    fun deleteSelectedInboxMessages(){
        inboxAdapter?.deleteSelectedInboxMessages()
        clearMessagesAndResetUI()
    }

    private fun openSearch() {
        bindingInboxBinding.etSearchText.setText("")
        bindingInboxBinding.rlSearchOpened.visibility = View.VISIBLE
        val circularReveal = ViewAnimationUtils.createCircularReveal(
            bindingInboxBinding.rlSearchOpened,
            (bindingInboxBinding.ivOpenSearch.right + bindingInboxBinding.ivOpenSearch.left) / 2,
            (bindingInboxBinding.ivOpenSearch.top + bindingInboxBinding.ivOpenSearch.bottom) / 2,
            0f, bindingInboxBinding.flSearchBar.width.toFloat() - 100
        )
        circularReveal.duration = 300
        circularReveal.start()
    }

    private fun closeSearch() {
        inboxAdapter?.setUnselected()
        val circularConceal = ViewAnimationUtils.createCircularReveal(
            bindingInboxBinding.rlSearchOpened,
            (bindingInboxBinding.ivOpenSearch.right + bindingInboxBinding.ivOpenSearch.left) / 2,
            (bindingInboxBinding.ivOpenSearch.top + bindingInboxBinding.ivOpenSearch.bottom) / 2,
            bindingInboxBinding.flSearchBar.width.toFloat(), 0f
        )

        circularConceal.duration = 300
        circularConceal.start()
        circularConceal.addListener(object : Animator.AnimatorListener {
            override fun onAnimationRepeat(animation: Animator?) = Unit
            override fun onAnimationCancel(animation: Animator?) = Unit
            override fun onAnimationStart(animation: Animator?) = Unit
            override fun onAnimationEnd(animation: Animator?) {
                bindingInboxBinding.rlSearchOpened.visibility = View.INVISIBLE
                bindingInboxBinding.etSearchText.setText("")
                circularConceal.removeAllListeners()
            }
        })
    }

}