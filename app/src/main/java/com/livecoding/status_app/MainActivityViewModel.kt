package com.livecoding.status_app

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.livecoding.status_app.data.local.LocalPref

class MainActivityViewModel(application: Application) : AndroidViewModel(application) {


    private val messageMutableLiveData = MutableLiveData<String>();
    val messageLiveData: LiveData<String> = messageMutableLiveData;

    val localPref by lazy {
        LocalPref.getInstance(application)
    }


    fun saveMessage(message: String) {
        localPref.addMessage(message)
        messageMutableLiveData.postValue(message)
    }


    fun getLastMessage() {
        messageMutableLiveData.postValue(localPref.getLastMessage())
    }

}