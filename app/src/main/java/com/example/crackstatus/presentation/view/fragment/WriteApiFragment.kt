package com.example.crackstatus.presentation.view.fragment

import android.content.Intent
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.example.crackstatus.R
import com.example.crackstatus.databinding.ActivityScannerBinding
import com.example.crackstatus.databinding.FragmentWriteApiBinding
import com.example.crackstatus.presentation.view.activity.ChatActivity
import com.example.crackstatus.utils.Util

class WriteApiFragment : Fragment() {
    private lateinit var binding: FragmentWriteApiBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
       binding = FragmentWriteApiBinding.inflate(inflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.buttonConnectApi.setOnClickListener {
            if (binding.editTextApiKey.text.isNotEmpty()) {
//                Toast.makeText(context, binding.editTextApiKey, Toast.LENGTH_SHORT).show()
                startActivity(
                    Intent(activity, ChatActivity::class.java).putExtra(Util.API_KEY.toString(),
                       binding.editTextApiKey.text.toString()))
            }
        }
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            WriteApiFragment()
    }
}