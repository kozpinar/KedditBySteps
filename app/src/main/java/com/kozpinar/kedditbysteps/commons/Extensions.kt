@file:JvmName("ExtensionsUtils")

package com.kozpinar.kedditbysteps.commons

import android.text.TextUtils
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import com.kozpinar.kedditbysteps.R
import com.squareup.picasso.Picasso

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */


fun ViewGroup.inflate(layoutId: Int, attachToRoot: Boolean = false): View =
        LayoutInflater.from(context).inflate(layoutId, this, attachToRoot)

fun ImageView.loadingImg(imageUrl: String) {
    if (TextUtils.isEmpty(imageUrl)) {
        Picasso.with(context).load(R.mipmap.ic_launcher).into(this)
    } else {
        Picasso.with(context).load(imageUrl).into(this)
    }
}