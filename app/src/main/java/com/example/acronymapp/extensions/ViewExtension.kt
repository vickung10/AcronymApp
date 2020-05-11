package com.example.acronymapp.extensions

import android.view.View
import androidx.databinding.BindingAdapter

@BindingAdapter("showView")
fun View.show(show: Boolean?) {
    visibility = when (show) {
        true -> View.VISIBLE
        false -> View.GONE
        else -> View.INVISIBLE
    }
}