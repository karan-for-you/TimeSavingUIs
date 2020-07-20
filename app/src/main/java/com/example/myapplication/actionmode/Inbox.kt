package com.example.myapplication.actionmode

import com.example.myapplication.actionmode.InboxAdapter

data class Inbox(
    val senderName : String,
    val senderEmail : String,
    val message : String,
    val timeStamp : String,
    val colorAssociated : Int,
    var isSelected : Boolean
)