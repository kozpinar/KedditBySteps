package com.kozpinar.kedditbysteps.commons.adapters

import android.support.v7.widget.RecyclerView
import android.view.ViewGroup

/**
 * Created by onur on 22.08.2017. KedditBySteps
 */

interface ViewTypeDelegateAdapter {
    fun onCreateViewHolder(parent: ViewGroup): RecyclerView.ViewHolder
    fun onBindViewHolder(holder: RecyclerView.ViewHolder, item: ViewType)
}