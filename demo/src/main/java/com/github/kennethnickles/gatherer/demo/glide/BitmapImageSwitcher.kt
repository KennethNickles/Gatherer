package com.github.kennethnickles.gatherer.demo.glide

import android.content.Context
import android.graphics.Bitmap
import android.support.annotation.NonNull
import android.util.AttributeSet
import android.widget.ImageSwitcher
import android.widget.ImageView

/**
 * @author kenneth.nickles
 * @since 2015-11-16.
 */
open class BitmapImageSwitcher : ImageSwitcher {

    constructor(context: Context) : super(context) {
    }

    constructor(context: Context, attribteSet: AttributeSet) : super(context, attribteSet) {
    }

    fun setImageBitmap(@NonNull bitmap: Bitmap) {
        val image = this.nextView as ImageView
        image.setImageBitmap(bitmap)
        showNext()
    }
}