package com.livecoding.status_app.data.local

import android.content.Context
import android.content.SharedPreferences

const val THIRD_PARTY_APP_MESSAGE_KEY = "thirdPartyAppMessageKey"


class LocalPref {

    private var sharedPreferences: SharedPreferences

    private constructor(context: Context) {
        sharedPreferences = context.getSharedPreferences("local", Context.MODE_PRIVATE);
    }


    companion object {
        var instance: LocalPref? = null;
        val lock = ""
        fun getInstance(context: Context) = instance ?: synchronized(lock) {
            instance ?: LocalPref(context).also {
                instance = it
            }
        }
    }


    fun addMessage(message: String) {
        sharedPreferences.edit().apply {
            putString(THIRD_PARTY_APP_MESSAGE_KEY, message)
            apply()
        }
    }


    fun getLastMessage() = sharedPreferences.getString(THIRD_PARTY_APP_MESSAGE_KEY, "")


}