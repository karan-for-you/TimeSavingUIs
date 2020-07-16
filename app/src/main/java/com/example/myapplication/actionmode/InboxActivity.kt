package com.example.myapplication.actionmode

import android.os.Bundle
import androidx.appcompat.view.ActionMode
import androidx.core.content.ContextCompat
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.myapplication.R
import com.example.myapplication.base.BaseActivity
import com.example.myapplication.databinding.ActivityInboxBinding
import com.example.myapplication.model.Inbox
import com.example.myapplication.viewmodel.InboxViewModel
import java.util.*

class InboxActivity : BaseActivity(),  InboxAdapter.OnClickListeners {

    lateinit var bindingInboxBinding : ActivityInboxBinding
    lateinit var inboxViewModel : InboxViewModel
    var inboxList = ArrayList<Inbox>()
    var inboxAdapter : InboxAdapter? = null
    var actionMode : ActionMode? = null
    var actionModeCallback : ActionModeCallBack? = null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingInboxBinding = DataBindingUtil.setContentView(this,R.layout.activity_inbox)
        setSupportActionBar(bindingInboxBinding.toolbar)
        supportActionBar?.title = "Inbox"
        prepareActionModeCallback()
        setupViewModelAndHandler()
        setupInboxRecyclerView()
        inboxViewModel.prepareLiveData()
        inboxViewModel.getInboxLiveData().observe(this,
            Observer<List<Inbox>> { t -> inboxAdapter?.addData(t!!) })
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
            context = this,
            inboxClickListener = this,
            inboxActivity = this)
        bindingInboxBinding.recyclerViewInbox.apply {
            layoutManager = LinearLayoutManager(this@InboxActivity)
            adapter = inboxAdapter
        }
    }

    fun finishActionMode(){
        if(actionMode!=null){
            actionMode!!.finish()
            actionMode = null
            inboxAdapter?.selectedInboxMessages?.clear() // Clear previous Data
            setNormalActionBarColorScheme()
            inboxAdapter?.listSelectedInbox()

        }
    }

    override fun onClicked(inbox: Inbox, position : Int, holder: InboxAdapter.InboxViewHolder) {
        if(actionMode!=null)
            inboxAdapter?.selectInboxMessages(holder, position)
        else showToast(inbox.senderName)
    }

    override fun onLongClicked(inbox: Inbox, position : Int) {
        if(actionMode == null){
            actionMode = startSupportActionMode(actionModeCallback!!)
        }
    }

    fun setActionModeColorScheme(){
        bindingInboxBinding.toolbar.setBackgroundColor(
            ContextCompat.getColor(this,R.color.colorRedBtn))
    }

    fun setNormalActionBarColorScheme(){
        bindingInboxBinding.toolbar.setBackgroundColor(
            ContextCompat.getColor(this,R.color.red))
        finishActionMode()
    }

    fun deleteSelectedInboxMessages(){
        inboxAdapter?.deleteSelectedInboxMessages()
        finishActionMode()
    }

}