package com.example.myapplication.model

data class Inbox(
    val senderName : String,
    val senderEmail : String,
    val message : String,
    val timeStamp : String,
    val colorAssociated : Int
)