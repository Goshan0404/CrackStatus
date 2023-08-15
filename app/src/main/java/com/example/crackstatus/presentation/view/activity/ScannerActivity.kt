package com.example.crackstatus.presentation.view.activity

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import android.view.View
import android.widget.TextView
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.example.crackstatus.R
import com.example.crackstatus.databinding.ActivityScannerBinding
import com.example.crackstatus.presentation.view.fragment.ChatFragment
import com.example.crackstatus.presentation.view.fragment.ScannerFragment
import com.example.crackstatus.presentation.view.fragment.WriteApiFragment
import com.example.crackstatus.utils.Util



class ScannerActivity : AppCompatActivity() {
    private lateinit var binding: ActivityScannerBinding


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityScannerBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportFragmentManager.beginTransaction().replace(R.id.frame_layout_scanner, ScannerFragment.newInstance()).commit()

    }




}