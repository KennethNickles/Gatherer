package com.github.kennethnickles.gatherer.demo.glide

import android.content.Context
import android.util.AttributeSet
import com.github.kennethnickles.gatherer.demo.R

/**
 * @author kenneth.nickles
 * @since 2015-11-04.
 */
class CrossfadeViewTarget : AnimatedViewTarget {

    constructor(context: Context) : super(context) {
        setAnimationResources(R.anim.fadein, R.anim.fadeout)
    }

    constructor(context: Context, attributeSet: AttributeSet) : super(context, attributeSet) {
        setAnimationResources(R.anim.fadein, R.anim.fadeout)
    }
}