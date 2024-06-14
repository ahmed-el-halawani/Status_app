package com.livecoding.status_app

import android.app.Activity
import android.os.Bundle
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.view.ViewCompat
import androidx.core.view.WindowInsetsCompat
import com.livecoding.status_app.data.local.THIRD_PARTY_APP_MESSAGE_KEY
import com.livecoding.status_app.databinding.ActivityMainBinding


class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainActivityViewModel>()

    private val binding by lazy {
        ActivityMainBinding.inflate(layoutInflater)
    }


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContentView(binding.root)

        initHistory();
        initIntentChecker();
        initObservers();
        initActions();

    }

    private fun initActions() {
        binding.btnFailure.setOnClickListener {
            returnWithResult(false)
        }

        binding.btnSuccess.setOnClickListener {
            returnWithResult(true)

        }
    }

    private fun returnWithResult(result: Boolean) {
        setResult(if (result) Activity.RESULT_OK else Activity.RESULT_CANCELED)
        finish()
    }


    private fun initHistory() {
        viewModel.getLastMessage()
    }

    private fun initIntentChecker() {
        if (intent.extras != null && intent.extras!!.containsKey(THIRD_PARTY_APP_MESSAGE_KEY)) {
            viewModel.saveMessage(intent.extras!!.getString(THIRD_PARTY_APP_MESSAGE_KEY)!!)
        }
    }

    private fun initObservers() {
        viewModel.messageLiveData.observe(this) {
            binding.tvMessage.text = it
        }
    }
}