package com.github.kennethnickles.gatherer.demo.glide

import android.view.View
import android.view.ViewTreeObserver
import com.bumptech.glide.request.target.SizeReadyCallback
import java.util.*

/**
 * @author kenneth.nickles
 * @since 2015-11-10.
 */
class ViewSizeDeterminer : ViewTreeObserver.OnPreDrawListener {

    private val view: View
    private val sizeReadyCallbacks: MutableList<SizeReadyCallback> = ArrayList(0)

    private var viewSizeDetermined: Boolean = false

    private var width: Int = 0
    private var height: Int = 0

    constructor(view: View) {
        this.view = view
    }

    override fun onPreDraw(): Boolean {
        if (view.width > 0 && view.height > 0) {
            width = view.width
            height = view.height
            viewSizeDetermined = true
        }
        if (!viewSizeDetermined) {
            return true
        }
        for (sizeReadyCallBack in sizeReadyCallbacks) {
            sizeReadyCallBack.onSizeReady(view.width, view.height)
        }
        sizeReadyCallbacks.clear()
        return true
    }

    fun addSizeReadyCallback(sizeReadyCallback: SizeReadyCallback) {
        if (!sizeReadyCallbacks.contains(sizeReadyCallback)) {
            sizeReadyCallbacks.add(sizeReadyCallback)
        }
        if (viewSizeDetermined) {
            for (sizeReadyCallBack in sizeReadyCallbacks) {
                sizeReadyCallBack.onSizeReady(view.width, view.height)
            }
            sizeReadyCallbacks.clear()
        }
    }
}