package com.example.myapplication.actionmode

import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.view.ActionMode
import com.example.myapplication.R

class ActionModeCallBack(var inboxActivity: InboxActivity) : ActionMode.Callback{

    override fun onActionItemClicked(mode: ActionMode?, item: MenuItem?): Boolean {
        when(item?.itemId){
            R.id.action_delete->{
                inboxActivity.deleteSelectedInboxMessages()
                mode?.finish()
            }

        }
        return true
    }

    override fun onCreateActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        inboxActivity.setActionModeColorScheme()
        mode?.menuInflater?.inflate(R.menu.action_mode_menu,menu)
        return true
    }

    override fun onPrepareActionMode(mode: ActionMode?, menu: Menu?): Boolean {
        return false
    }

    override fun onDestroyActionMode(mode: ActionMode?) {
        inboxActivity.setNormalActionBarColorScheme()

    }
}