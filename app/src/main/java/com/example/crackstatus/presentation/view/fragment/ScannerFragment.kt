package com.example.crackstatus.presentation.view.fragment

import android.Manifest
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.View.OnClickListener
import android.view.ViewGroup
import android.widget.TextView
import android.widget.Toast
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.budiyev.android.codescanner.CodeScanner
import com.budiyev.android.codescanner.CodeScannerView
import com.budiyev.android.codescanner.DecodeCallback
import com.budiyev.android.codescanner.ErrorCallback
import com.example.crackstatus.R
import com.example.crackstatus.databinding.FragmentScannerBinding
import com.example.crackstatus.presentation.view.activity.ChatActivity

import com.example.crackstatus.utils.Util

private const val REQUEST_CODE = 101
class ScannerFragment : Fragment(), OnClickListener {
    private lateinit var binding: FragmentScannerBinding
    private lateinit var button: TextView
    private lateinit var codeScanner: CodeScanner
    private lateinit var scannerView: CodeScannerView

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        binding = FragmentScannerBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        scannerView = binding.scannerView
        button = binding.button
        scannerView.setOnClickListener(this)
        button.setOnClickListener(this)

        setPermission()
        setScanner()
    }

    companion object {
        @JvmStatic
        fun newInstance() =
            ScannerFragment()
    }

    private fun setScanner() {
        codeScanner = CodeScanner(requireContext(), scannerView)
        codeScanner.decodeCallback = DecodeCallback {
            activity?.runOnUiThread {
                startActivity(
                    Intent(requireActivity(), ChatActivity::class.java).putExtra(
                        Util.API_KEY.toString(),
                        it.toString()
                    )
                )
            }
        }
        codeScanner.errorCallback = ErrorCallback { // or ErrorCallback.SUPPRESS
            activity?.runOnUiThread {
                Toast.makeText(
                    context, "Qr код не распознан",
                    Toast.LENGTH_SHORT
                ).show()
            }
        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            scannerView -> codeScanner.startPreview()
            button ->
                activity?.supportFragmentManager?.beginTransaction()
                    ?.replace(R.id.frame_layout_scanner, WriteApiFragment.newInstance())
                    ?.addToBackStack(null)
                    ?.commit()
        }

    }

    private fun setPermission() {
        val permission = ContextCompat.checkSelfPermission(requireContext(), Manifest.permission.CAMERA)
        if (permission != PackageManager.PERMISSION_GRANTED) {
            makeRequest()
        }
    }

    private fun makeRequest() {
        ActivityCompat.requestPermissions(requireActivity(), arrayOf(Manifest.permission.CAMERA), REQUEST_CODE)
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
        when(requestCode) {
            REQUEST_CODE -> {
                if (grantResults.isEmpty() || grantResults[0] != PackageManager.PERMISSION_GRANTED) {
                    Toast.makeText(
                        context,
                        "Разрешите использовать камеру для приложения",
                        Toast.LENGTH_SHORT
                    ).show()
                }
            }
        }
    }

    override fun onResume() {
        super.onResume()
        codeScanner.startPreview()
    }

    override fun onPause() {
        codeScanner.releaseResources()
        super.onPause()
    }
}