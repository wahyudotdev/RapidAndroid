package com.rapidcore.core.extension

import android.text.TextUtils
import android.util.Patterns
import java.util.regex.Pattern

@Suppress("unused")
fun String?.isValidEmail(): Boolean{
    return if (this != null){
        !TextUtils.isEmpty(this) && Patterns.EMAIL_ADDRESS.matcher(this).matches()
    } else {
        false
    }
}

@Suppress("unused")
fun String?.isNotNullOrEmpty(): Boolean{
    return this?.isNotEmpty() ?: false
}

@Suppress("unused")
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