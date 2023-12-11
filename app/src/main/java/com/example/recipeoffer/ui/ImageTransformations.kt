package com.example.recipeoffer.ui

import android.graphics.Bitmap
import com.squareup.picasso.Transformation

class DeleteExtraBorderTransformation : Transformation {

    private val frameSizeX = 40
    private val frameSizeY = 20
    override fun transform(source: Bitmap?): Bitmap {

        source?.let {
            val width = source.width - 2 * frameSizeX
            val height = source.height - 2 * frameSizeY
            val result = Bitmap.createBitmap(source, frameSizeX, frameSizeY, width, height)

            if (result != source) {
                source.recycle()
            }

            return result
        }
        throw IllegalStateException("No source for image transformation")
    }

    override fun key(): String {
        return "delete extra border $frameSizeX $frameSizeY"
    }

}