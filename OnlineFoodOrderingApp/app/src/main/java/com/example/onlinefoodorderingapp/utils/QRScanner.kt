package com.example.foodordering.utils

import android.app.Activity
import android.content.Intent
import com.google.zxing.integration.android.IntentIntegrator

object QRScanner {

    fun startScan(activity: Activity) {
        val integrator = IntentIntegrator(activity)
        integrator.setDesiredBarcodeFormats(IntentIntegrator.QR_CODE)
        integrator.setPrompt("Scan Order QR Code")
        integrator.setCameraId(0)
        integrator.setBeepEnabled(true)
        integrator.setBarcodeImageEnabled(false)
        integrator.initiateScan()
    }

    fun handleResult(
        requestCode: Int,
        resultCode: Int,
        data: Intent?,
        onResult: (String?) -> Unit
    ) {
        val result = IntentIntegrator.parseActivityResult(requestCode, resultCode, data)
        if (result != null) {
            onResult(result.contents)
        }
    }
}
