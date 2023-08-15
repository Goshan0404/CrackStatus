package com.example.crackstatus.presentation.view.activity

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.crackstatus.R
import com.example.crackstatus.databinding.ActivityChatBinding
import com.example.crackstatus.presentation.view.fragment.ChatFragment
import com.example.crackstatus.utils.Util

class ChatActivity : AppCompatActivity() {
    private lateinit var binding: ActivityChatBinding
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityChatBinding.inflate(layoutInflater)
        setContentView(binding.root)

        val  bundle = Bundle()


        val intent = intent
        val apiKey = intent.getStringExtra(Util.API_KEY.toString())
        bundle.putString(Util.API_KEY.toString(), apiKey)
        val chatFragment = ChatFragment.newInstance()
        chatFragment.arguments = bundle

        supportFragmentManager.beginTransaction().add(R.id.frame_layout_chat, chatFragment).commit()
    }
}