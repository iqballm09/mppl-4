package com.example.foodcourtpayclient.session

import android.content.Context
import com.example.foodcourtpayclient.data.UserModel

internal class UserPreferences(context: Context) {
    companion object {
        private const val PREFS_NAME = "user_pref"
        private const val TOKEN = "token"
        private const val LOGIN = "islogin"
        private const val NAME = "name"
        private const val ID = "id"
    }

    private val preferences = context.getSharedPreferences(PREFS_NAME, Context.MODE_PRIVATE)

    fun setUser(value: String, name: String, id_user: Int) {
        val editor = preferences.edit()
        editor.putString(TOKEN, value)
        editor.putString(NAME, name)
        editor.putInt(ID, id_user)
        editor.putBoolean(LOGIN, true)
        editor.apply()
    }

    fun getUser(): UserModel {
        val model = UserModel()
        model.token = preferences.getString(TOKEN, "").toString()
        model.isLogin = preferences.getBoolean(LOGIN, false)
        model.name = preferences.getString(NAME, "").toString()
        model.id = preferences.getInt(ID, -1)
        return model
    }

    fun deleteUser() {
        preferences.edit().remove("token").apply()
        preferences.edit().putBoolean("islogin", false).apply()
        preferences.edit().remove("name").apply()
        preferences.edit().remove("id").apply()
    }
}