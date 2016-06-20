package com.github.kennethnickles.gatherer.demo.glide

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.support.annotation.AnimRes
import android.support.annotation.NonNull
import android.support.annotation.Nullable
import android.util.AttributeSet
import android.util.Log
import android.widget.ImageView
import com.bumptech.glide.request.Request
import com.bumptech.glide.request.animation.GlideAnimation
import com.bumptech.glide.request.target.SizeReadyCallback
import com.bumptech.glide.request.target.Target

/**
 * @author kenneth.nickles
 * @since 2016-7-14.
 */
open class AnimatedViewTarget : BitmapImageSwitcher, Target<Bitmap> {

    private val TAG: String = AnimatedViewTarget::class.java.simpleName
    private val placeholderImageView: ImageView
    private val bitmapImageView: ImageView
    private val placeholderViewSizeDeterminer: ViewSizeDeterminer
    private val bitmapViewSizeDeterminer: ViewSizeDeterminer

    @AnimRes
    private var inAnimationResourceId: Int = 0
    @AnimRes
    private var outAnimationResourceId: Int = 0

    private var isBitmapLoaded: Boolean = false

    constructor(context: Context) : super(context) {
        this.animateFirstView = false
        this.placeholderImageView = ImageView(context)
        this.bitmapImageView = ImageView(context)
        addView(placeholderImageView)
        addView(bitmapImageView)
        placeholderViewSizeDeterminer = ViewSizeDeterminer(placeholderImageView)
        bitmapViewSizeDeterminer = ViewSizeDeterminer(bitmapImageView)
        placeholderImageView.viewTreeObserver.addOnPreDrawListener(placeholderViewSizeDeterminer)
        bitmapImageView.viewTreeObserver.addOnPreDrawListener(bitmapViewSizeDeterminer)
    }

    constructor(context: Context, attribteSet: AttributeSet) : super(context, attribteSet) {
        this.animateFirstView = false
        this.placeholderImageView = ImageView(context)
        this.bitmapImageView = ImageView(context)
        addView(placeholderImageView)
        addView(bitmapImageView)
        placeholderViewSizeDeterminer = ViewSizeDeterminer(placeholderImageView)
        bitmapViewSizeDeterminer = ViewSizeDeterminer(bitmapImageView)
        placeholderImageView.viewTreeObserver.addOnPreDrawListener(placeholderViewSizeDeterminer)
        bitmapImageView.viewTreeObserver.addOnPreDrawListener(bitmapViewSizeDeterminer)
    }

    override fun onStart() {
        // Do Nothing
    }

    override fun onDestroy() {
        if (isBitmapLoaded) {
            clearAnimation()
        }
        placeholderImageView.viewTreeObserver.removeOnPreDrawListener(placeholderViewSizeDeterminer)
        bitmapImageView.viewTreeObserver.removeOnPreDrawListener(bitmapViewSizeDeterminer)
    }

    override fun onStop() {
        if (isBitmapLoaded) {
            clearAnimation()
        }
    }

    override fun onLoadFailed(@Nullable e: Exception?, @Nullable errorDrawable: Drawable?) {
        Log.e(TAG, Log.getStackTraceString(e))
        placeholderImageView.setImageDrawable(errorDrawable)
        inAnimation = null
        outAnimation = null
        if (isBitmapLoaded) {
            showPrevious()
            bitmapImageView.setImageBitmap(null)
        }
        isBitmapLoaded = false
    }

    override fun onLoadCleared(@Nullable placeholder: Drawable?) {
        Log.d(TAG, "onLoadCleared")
        placeholderImageView.setImageDrawable(placeholder)
        inAnimation = null
        outAnimation = null
        if (isBitmapLoaded) {
            showPrevious()
            bitmapImageView.setImageBitmap(null)
        }
        isBitmapLoaded = false
    }

    @Nullable
    override fun getRequest(): Request? {
        return tag as Request?
    }

    override fun onResourceReady(resource: Bitmap?, glideAnimation: GlideAnimation<in Bitmap>?) {
        Log.d(TAG, "onResourceReady")
        bitmapImageView.setImageBitmap(resource)
        showNext()
        placeholderImageView.setImageDrawable(null)
        isBitmapLoaded = true
        inAnimation = null
        outAnimation = null
    }

    override fun setRequest(@Nullable request: Request?) {
        tag = request
    }

    override fun onLoadStarted(@Nullable placeholder: Drawable?) {
        Log.d(TAG, "onLoadStarted")
        placeholderImageView.setImageDrawable(placeholder)
        if (isBitmapLoaded) {
            showPrevious()
            bitmapImageView.setImageBitmap(null)
        }
        isBitmapLoaded = false
        if (inAnimationResourceId > 0 && outAnimationResourceId > 0) {
            setInAnimation(context, inAnimationResourceId)
            setOutAnimation(context, outAnimationResourceId)
        }
    }

    override fun getSize(@NonNull cb: SizeReadyCallback) {
        if (isBitmapLoaded) {
            bitmapViewSizeDeterminer.addSizeReadyCallback(cb)
        } else {
            placeholderViewSizeDeterminer.addSizeReadyCallback(cb)
        }
    }

    fun setAnimationResources(@AnimRes inAnimationResourceId: Int, @AnimRes outAnimationResourceId: Int) {
        this.inAnimationResourceId = inAnimationResourceId
        this.outAnimationResourceId = outAnimationResourceId
    }
}
