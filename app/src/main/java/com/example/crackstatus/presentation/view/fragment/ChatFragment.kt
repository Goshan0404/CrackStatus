package com.example.crackstatus.presentation.view.fragment

import android.content.Intent
import android.os.Bundle
import android.text.Editable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.fragment.app.Fragment
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.crackstatus.RecyclerViewAdapter
import com.example.crackstatus.databinding.FragmentChatBinding
import com.example.crackstatus.presentation.view.activity.ScannerActivity
import com.example.crackstatus.presentation.viewModel.ChatFragmentViewModel
import com.example.crackstatus.presentation.viewModel.provider.ChatViewModelProvider
import com.example.crackstatus.utils.Util


class ChatFragment : Fragment() {
    private lateinit var binding: FragmentChatBinding
    private lateinit var apiKey: String
    private lateinit var messageDisabled: String
    private lateinit var messageWorker: String
    private lateinit var recyclerViewAdapter: RecyclerViewAdapter
    private val messages = mutableListOf<String>()
    private lateinit var chatViewModel: ChatFragmentViewModel


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentChatBinding.inflate(layoutInflater, container, false)
        apiKey = arguments?.getString(Util.API_KEY.toString()).toString()
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        recyclerViewAdapter = RecyclerViewAdapter(messages)

        binding.recyclerViewMessage.layoutManager = LinearLayoutManager(context)
        binding.recyclerViewMessage.adapter = recyclerViewAdapter
        chatViewModel = ViewModelProvider(
            this,
            ChatViewModelProvider(apiKey)
        ).get(ChatFragmentViewModel::class.java)

        chatViewModel.messageDisabled.observe(viewLifecycleOwner) {
            binding.editTextCurrentMessage.text = Editable.Factory.getInstance()
                .newEditable(binding.editTextCurrentMessage.text.toString() + " " + it.toString())
        }
        chatViewModel.messageWorker.observe(viewLifecycleOwner) {
            messages.add("Оператор: " + it.toString())
            binding.textViewCorrespondence.visibility = View.GONE
            binding.recyclerViewMessage.visibility = View.VISIBLE
            recyclerViewAdapter.notifyDataSetChanged()
        }

        binding.buttonSendMessage.setOnClickListener {
            messageDisabled = binding.editTextCurrentMessage.text.toString()
            if (messageDisabled != null) {
                sendMessage()
            } else {
                Toast.makeText(context, "Текст пустой", Toast.LENGTH_SHORT).show()
            }
        }

        binding.buttonQuestionPoint.setOnClickListener {
            messageDisabled = binding.editTextCurrentMessage.text.toString()
            if (messageDisabled != null) {
                binding.editTextCurrentMessage.text = Editable.Factory.getInstance()
                    .newEditable(binding.editTextCurrentMessage.text.toString() + "?")
                sendMessage()
            } else {
            Toast.makeText(context, "Текст пустой", Toast.LENGTH_SHORT).show()
        }
        }

        binding.buttonExclamatoryPoint.setOnClickListener {
            messageDisabled = binding.editTextCurrentMessage.text.toString()
            if (messageDisabled != null) {
                binding.editTextCurrentMessage.text = Editable.Factory.getInstance()
                    .newEditable(binding.editTextCurrentMessage.text.toString() + "!")
                sendMessage()
            } else {
                Toast.makeText(context, "Текст пустой", Toast.LENGTH_SHORT).show()
            }
        }

        binding.floatingActionButton.setOnClickListener {
            startActivity(Intent(context, ScannerActivity::class.java))
        }


        binding.buttonRewriteMessage.setOnClickListener {
            binding.editTextCurrentMessage.text = null
        }


    }

    private fun sendMessage() {
        binding.textViewCorrespondence.visibility = View.GONE
        binding.recyclerViewMessage.visibility = View.VISIBLE
        chatViewModel.sendMessage(binding.editTextCurrentMessage.text.toString())
        messages.add("Я: " + binding.editTextCurrentMessage.text.toString())
        recyclerViewAdapter.notifyDataSetChanged()
        binding.editTextCurrentMessage.text = null
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ChatFragment()
    }
}