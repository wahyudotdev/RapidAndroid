package com.rapidandroid.core.extension

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

fun String?.isValidEmail(): Boolean{
    return if (this != null){
        !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    } else {
        false
    }
}

fun String?.isNotNullOrEmpty(): Boolean{
    return this?.isNotEmpty() ?: false
}

fun String?.isValidPassword(): Boolean {
    val passwordREGEX = Pattern.compile("^" +
            "(?=.*[0-9])" +         //at least 1 digit
            "(?=.*[a-z])" +         //at least 1 lower case letter
            "(?=.*[a-zA-Z])" +      //any letter
            ".{8,}" +               //at least 8 characters
            "$")
    this?.let {
        return passwordREGEX.matcher(this).matches()
    } ?: return false
}