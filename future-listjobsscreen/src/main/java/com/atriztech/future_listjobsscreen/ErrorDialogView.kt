package com.atriztech.future_listjobsscreen

import android.app.AlertDialog
import android.view.LayoutInflater
import android.widget.Button
import javax.inject.Inject

class ErrorDialogView @Inject constructor() {
    private lateinit var dialogBuilder: AlertDialog.Builder
    private lateinit var alertDialog: AlertDialog

    fun showErrorDialog(layoutInflater: LayoutInflater){
        dialogBuilder = AlertDialog.Builder(layoutInflater.context)
        var view = layoutInflater.inflate(R.layout.error_dialog, null)
        var button = view.findViewById(R.id.close_error_dialog) as Button

        dialogBuilder.setView(view)
        alertDialog = dialogBuilder.create()
        alertDialog.show()

        button.setOnClickListener {
            alertDialog.dismiss()
        }
    }
}