package com.infinitumcode.hackernews.utils

import android.content.Context
import es.dmoral.toasty.Toasty

fun Context.showErrorMessage(message: String) {
    Toasty.error(this, message).show()
}

fun Context.showWarningMessage(message: String) {
    Toasty.warning(this, message).show()
}
