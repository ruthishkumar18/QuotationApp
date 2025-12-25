package com.example.onlinefoodorderingapp.utils

import android.graphics.Bitmap
import android.graphics.Color
import com.google.zxing.BarcodeFormat
import com.google.zxing.MultiFormatWriter
import com.example.onlinefoodorderingapp.utils.Constants

object QRGenerator {

    fun generate(data: String): Bitmap {
        val bitMatrix = MultiFormatWriter().encode(
            data,
            BarcodeFormat.QR_CODE,
            Constants.QR_SIZE,
            Constants.QR_SIZE
        )

        val bitmap =
            Bitmap.createBitmap(Constants.QR_SIZE, Constants.QR_SIZE, Bitmap.Config.RGB_565)

        for (x in 0 until Constants.QR_SIZE) {
            for (y in 0 until Constants.QR_SIZE) {
                bitmap.setPixel(
                    x,
                    y,
                    if (bitMatrix[x, y]) Color.BLACK else Color.WHITE
                )
            }
        }
        return bitmap
    }
}
