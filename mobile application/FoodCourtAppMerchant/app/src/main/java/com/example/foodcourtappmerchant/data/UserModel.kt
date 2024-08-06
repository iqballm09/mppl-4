package com.example.foodcourtappmerchant.data

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class UserModel (
        var name: String? = null,
        var id:Int? = null,
        var token: String? = null,
        var isLogin:Boolean = false
): Parcelable