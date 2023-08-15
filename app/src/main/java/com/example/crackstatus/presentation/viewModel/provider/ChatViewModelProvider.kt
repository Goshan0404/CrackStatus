package com.example.crackstatus.presentation.viewModel.provider

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.crackstatus.presentation.viewModel.ChatFragmentViewModel

class ChatViewModelProvider(private val apiKey: String): ViewModelProvider.Factory {

    override fun <T : ViewModel> create(modelClass: Class<T>): T {
        return ChatFragmentViewModel(apiKey) as T
    }
}