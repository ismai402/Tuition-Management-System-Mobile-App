package com.example.edututor.ui.shared.components

import android.app.Dialog
import android.content.Context
import android.view.LayoutInflater
import android.view.Window
import com.example.edututor.R

class LoadingDialog(context: Context) : Dialog(context) {
    init {
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContentView(LayoutInflater.from(context).inflate(R.layout.dialog_loading, null))
        setCancelable(false)
    }
}
