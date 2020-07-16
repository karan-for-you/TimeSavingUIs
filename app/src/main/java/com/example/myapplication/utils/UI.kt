package com.example.myapplication.utils

import android.app.Activity
import android.content.Context
import android.graphics.Color
import android.graphics.drawable.ColorDrawable
import android.view.LayoutInflater
import android.view.View
import android.view.inputmethod.InputMethodManager
import android.widget.TextView
import android.widget.Toast
import androidx.annotation.StringRes
import androidx.appcompat.app.AlertDialog
import com.example.myapplication.R


/**
 * Created by karanjeet on 22/10/19
 */
class UI {

    companion object {
        fun showToast(message: String, context: Context ): Toast? {
            return Toast.makeText(context, message, Toast.LENGTH_SHORT)
        }

        fun showToast(@StringRes resId: Int, context: Context ): Toast? {
            return Toast.makeText(context, resId, Toast.LENGTH_SHORT)
        }

        fun showLongToast(message: String, context: Context ): Toast? {
            return Toast.makeText(context, message, Toast.LENGTH_LONG)
        }

        fun showLongToast(@StringRes resId: Int, context: Context): Toast? {
            return Toast.makeText(context, resId, Toast.LENGTH_LONG)
        }

        fun hideSoftKeyboard(activity: Activity) {
            var view = activity.currentFocus
            if (view == null) view = View(activity)
            val im = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            im.hideSoftInputFromWindow(view.windowToken, 0)
        }

        fun showSoftKeyboard(activity: Activity) {
            var view = activity.currentFocus
            if (view == null) view = View(activity)
            val im = activity.getSystemService(Activity.INPUT_METHOD_SERVICE) as InputMethodManager
            im.showSoftInput(view, InputMethodManager.SHOW_IMPLICIT)
        }


        fun showProgressBar(context: Context): AlertDialog {
            val builder = AlertDialog.Builder(context)
            val dialog = builder.create()
            val dialogLayout: View =
                LayoutInflater.from(context).inflate(R.layout.progress_layout, null)
            dialog.setView(dialogLayout)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            return dialog
        }

        fun showProgressBar(context: Context, text: String): AlertDialog {
            val builder = AlertDialog.Builder(context)
            val dialog = builder.create()
            val dialogLayout: View =
                LayoutInflater.from(context).inflate(R.layout.progress_layout, null)
            val text1 = dialogLayout.findViewById(R.id.loading_msg) as TextView
            text1.text = text
            dialog.setView(dialogLayout)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            return dialog
        }

        fun showProgressBar(context: Context, resId: Int): AlertDialog {
            val builder = AlertDialog.Builder(context)
            val dialog = builder.create()
            val dialogLayout: View =
                LayoutInflater.from(context).inflate(R.layout.progress_layout, null)
            val text1 = dialogLayout.findViewById(R.id.loading_msg) as TextView
            text1.text = context.getString(resId)
            dialog.setView(dialogLayout)
            dialog.window?.setBackgroundDrawable(ColorDrawable(Color.TRANSPARENT))
            dialog.setCancelable(false)
            dialog.setCanceledOnTouchOutside(false)
            return dialog
        }
    }

}