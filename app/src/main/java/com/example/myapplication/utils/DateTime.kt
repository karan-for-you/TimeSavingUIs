package com.example.myapplication.utils

import java.text.DateFormat
import java.text.ParseException
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.TimeUnit


/**
 * Created by karanjeet on 12/11/19
 */
class DateTime {
    companion object {

        private fun millisecondToDate(millis: Long): String? {
            val formatter: DateFormat = SimpleDateFormat("dd MM yyyy")
            val calendar = Calendar.getInstance()
            calendar.timeInMillis = millis
            return formatter.format(calendar.time)
        }

        fun calculateDays(pastDate : Long) : Long {
            var dayDifference = 0L
            val simpleDateFormat = SimpleDateFormat("dd MM yyyy")
            val pastDateReceived = millisecondToDate(pastDate)
            val nowDate = millisecondToDate(System.currentTimeMillis())
            try {
                val date1: Date? = simpleDateFormat.parse(pastDateReceived!!)
                val date2: Date? = simpleDateFormat.parse(nowDate!!)
                val diff = date2!!.time - date1!!.time
                dayDifference = TimeUnit.DAYS.convert(
                    diff,
                    TimeUnit.MILLISECONDS
                )
                Logger.logError(
                    DateTime::class.java.simpleName,
                    "Days Elapsed: $dayDifference"
                )
            } catch (e: ParseException) {
                Logger.logError(DateTime::class.java.simpleName, "Error Calculating date")
            }
            return dayDifference
        }

        fun calculateMinutes(pastDate : Long) : Long {
            var minutesDifference = 0L
            try {
                val past =  pastDate
                val now = System.currentTimeMillis()
                val diff = now - past
                minutesDifference = diff / (60 * 1000) % 60
                Logger.logError(
                    DateTime::class.java.simpleName,
                    "Minutes Elapsed: $minutesDifference"
                )
            } catch (e: ParseException) {
                Logger.logError(DateTime::class.java.simpleName, "Error Calculating minutes")
            }
            return minutesDifference
        }
    }

}