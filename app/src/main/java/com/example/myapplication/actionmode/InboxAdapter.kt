package com.example.myapplication.actionmode

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.example.myapplication.R
import com.example.myapplication.utils.Logger
import java.lang.Exception
import java.util.*

class InboxAdapter(
    var inboxList: ArrayList<Inbox>,
    var context: Context,
    var inboxClickListener: OnClickListeners,
    var inboxActivity: InboxActivity
) : RecyclerView.Adapter<InboxAdapter.InboxViewHolder>() {

    val selectedInboxMessages = ArrayList<Inbox>()

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): InboxViewHolder {
        return InboxViewHolder(
            LayoutInflater.from(context).inflate(
                R.layout.inbox_layout,
                parent,
                false
            )
        )
    }

    override fun getItemCount(): Int {
        return inboxList.size
    }

    override fun onBindViewHolder(holder: InboxViewHolder, position: Int) {
        holder.bindItems(inboxList[position])
        holder.itemView.setOnClickListener {
            inboxList[position].isSelected = !inboxList[position].isSelected
            inboxClickListener.onClicked(inboxList[position], position = position, holder = holder)
        }
        holder.itemView.setOnLongClickListener {
            inboxList[position].isSelected = !inboxList[position].isSelected
            inboxClickListener.onLongClicked(inboxList[position], position = position,holder = holder)
            true
        }
    }

    fun selectInboxMessages(holder: InboxViewHolder, position: Int) {
        if (inboxList[position].isSelected) {
            holder.ivCheck.visibility = View.VISIBLE
            holder.clView.setBackgroundColor(ContextCompat.getColor(context, R.color.fbBlueFaded))
            selectedInboxMessages.add(inboxList[position])
        } else {
            holder.ivCheck.visibility = View.GONE
            holder.clView.setBackgroundColor(ContextCompat.getColor(context, R.color.white))
            selectedInboxMessages.remove(inboxList[position])
        }
        if (selectedInboxMessages.size == 0)
            inboxActivity.finishActionMode()
        listSelectedInbox()
    }

    fun listSelectedInbox() {
        for (inbox in selectedInboxMessages)
            Logger.logError("Selected", inbox.senderName)
        Logger.logError("Size of List", "" + selectedInboxMessages.size)
    }

    fun setUnselected(){
        try {
            for(inbox in inboxList) {
                inbox.isSelected = false
            }
            notifyDataSetChanged()
        }catch (e : Exception){
            Logger.logError("Called before pressing onBackPressed() in activity","Hanlding")
        }
    }

    fun deleteSelectedInboxMessages() {
        for (inbox in selectedInboxMessages) {
            if (inboxList.contains(inbox))
                inboxList.remove(inbox)
        }
        selectedInboxMessages.clear() // Clearing previous data
        notifyDataSetChanged()
    }

    class InboxViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val ivCheck = view.findViewById(R.id.ivCheck) as ImageView
        val clView = view.findViewById(R.id.clView) as ConstraintLayout
        private val tvNameInitials = view.findViewById(R.id.tvNameInitials) as TextView
        private val tvName = view.findViewById(R.id.tvName) as TextView
        private val tvEmail = view.findViewById(R.id.tvEmail) as TextView
        private val tvMessage = view.findViewById(R.id.tvMessage) as TextView
        private val tvTime = view.findViewById(R.id.tvTime) as TextView

        fun bindItems(inbox: Inbox) {
            tvName.text = inbox.senderName
            tvEmail.text = inbox.senderEmail
            tvMessage.text = inbox.message
            tvTime.text = inbox.timeStamp
            val nameArray = inbox.senderName.split(" ")
            val builder = StringBuilder()
            if (nameArray.size > 1) {
                builder.append(nameArray[0][0])
                builder.append(nameArray[1][0])
            } else builder.append(inbox.senderName[0].toString())
            tvNameInitials.text = builder.toString()
            tvNameInitials.setBackgroundColor(
                ContextCompat.getColor(
                    itemView.context,
                    inbox.colorAssociated
                )
            )
            if(inbox.isSelected){
                ivCheck.visibility = View.VISIBLE
                clView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.fbBlueFaded))
            }else{
                ivCheck.visibility = View.GONE
                clView.setBackgroundColor(ContextCompat.getColor(itemView.context, R.color.white))
            }
        }
    }

    fun addData(freshInboxList: List<Inbox>) {
        inboxList.addAll(freshInboxList)
        notifyDataSetChanged()
    }

    interface OnClickListeners {
        fun onClicked(inbox: Inbox, position: Int, holder: InboxViewHolder)
        fun onLongClicked(inbox: Inbox, position: Int, holder: InboxViewHolder)
    }

}