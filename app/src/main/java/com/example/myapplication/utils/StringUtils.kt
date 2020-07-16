package com.example.myapplication.utils

import android.content.Context
import android.text.SpannableString
import android.text.SpannableStringBuilder
import android.text.style.ForegroundColorSpan
import androidx.core.content.ContextCompat

/**
 * Created by karanjeet on 20/11/19
 */
class StringUtils {

    companion object{

    fun isNullOrEmpty(pStr: String?): Boolean {
        return pStr == null || pStr.trim { it <= ' ' }.isEmpty() || pStr.trim { it <= ' ' }.equals(
            "null",
            ignoreCase = true
        ) || "" == pStr
    }

    fun isNullOrEmptyOrZero(pStr: String?): Boolean {
        return pStr == null || pStr.trim { it <= ' ' }.isEmpty() || pStr.trim { it <= ' ' }.equals(
            "null",
            ignoreCase = true
        ) || pStr.trim { it <= ' ' }.equals("0", ignoreCase = true)
    }

    /**
     * Method returns the index of in the string where the stringToFind
     * starts in the fullString
     * @param stringToFind - Target string to find
     * @param fullString - The full string in which strings is to be found
     * @return
     */
    private fun isSubstring(stringToFind: String, fullString: String): Int {
        val m = stringToFind.length
        val n = fullString.length

        for (i in 0..n - m) {
            var j = 0
            while (j < m) {
                if (fullString[i + j] != stringToFind[j])
                    break
                j++
            }
            if (j == m)
                return i
        }

        return -1
    }

    fun createSpannableStringBuilder(
        fullString: String,
        stringToFind: String,
        color: Int,
        context: Context
    ): SpannableStringBuilder {
        val builder = SpannableStringBuilder()
        val ss = SpannableString(fullString)
        val startIndex = isSubstring(stringToFind, fullString)
        // Adding the startIndex to the length of stringToFind
        // so that entire word found is marked
        ss.setSpan(
            ForegroundColorSpan(ContextCompat.getColor(context, color)),
            startIndex,
            startIndex + stringToFind.length,
            0
        )
        builder.append(ss)
        return builder
    }


    }

}