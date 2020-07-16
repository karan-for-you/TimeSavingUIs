package com.example.myapplication.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.myapplication.R
import com.example.myapplication.model.Inbox
import java.util.*

class InboxViewModel : ViewModel() {

    var colorArray = arrayOf(
        R.color.colorGreenBtn,
        R.color.colorGreenBtnFade,
        R.color.colorRedBtn,
        R.color.colorRedBtnFade,
        R.color.colorPinkBar,
        R.color.colorKYCWait,
        R.color.colorYellow,
        R.color.colorKYCRejected,
        R.color.colorGreenBtn
    )

    var inboxLiveData = MutableLiveData<List<Inbox>>()

    fun getInboxLiveData(): LiveData<List<Inbox>> {
        return inboxLiveData
    }

    fun prepareLiveData() {
        val inboxList = ArrayList<Inbox>()
        inboxList.add(
            Inbox(
                senderName = "Himani Sharma",
                senderEmail = "himani@gmail.com",
                message = "Send me the test report please.",
                timeStamp = "12:30 pm",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Daljeet Singh",
                senderEmail = "daljeet@gmail.com",
                message = "Black Book release manager required.",
                timeStamp = "10:03 pm",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Babban Deep Singh",
                senderEmail = "babban@gmail.com",
                message = "Leaving for Gurdaspur. Have fun with PHP",
                timeStamp = "2:12 pm",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Sukirti",
                senderEmail = "sukirti@gmail.com",
                message = "Gonna Smoke up and design some pages.",
                timeStamp = "5.30 pm",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Asheesh Bhuria",
                senderEmail = "asheesh@gmail.com",
                message = "Lets go watch some Rick and Morty.",
                timeStamp = "9:30 am",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Renu Yadaav",
                senderEmail = "renu@gmail.com",
                message = "The managers are getting on my nerves.",
                timeStamp = "8:22 pm",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Satish Thakur",
                senderEmail = "satish@gmail.com",
                message = "I am gonna grid on some iOS.",
                timeStamp = "2:57 am",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Nitin Bhatt",
                senderEmail = "nitin@gmail.com",
                message = "i still am the number 1 on this groups list.",
                timeStamp = "12:30 pm",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Sushmit Gaur",
                senderEmail = "sushmit@gmail.com",
                message = "Let's go get some coffee.",
                timeStamp = "12:30 pm",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Ankur Bansala",
                senderEmail = "ankur@gmail.com",
                message = "Please send the status mail.",
                timeStamp = "3:49 pm",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxList.add(
            Inbox(
                senderName = "Mohit Chaudhary",
                senderEmail = "mohit@gmail.com",
                message = "Let's work on SMASHItv.",
                timeStamp = "3:12 pm",
                colorAssociated = colorArray[Random().nextInt(colorArray.size)]
            )
        )
        inboxLiveData.value = inboxList
    }

}