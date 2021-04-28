package com.example.requestpermission


import android.Manifest.*
import android.content.pm.PackageManager
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AlertDialog
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.app.ActivityCompat.requestPermissions
import androidx.core.content.ContextCompat
import androidx.core.content.PermissionChecker.PERMISSION_DENIED
import androidx.core.content.PermissionChecker.PERMISSION_GRANTED
import com.example.requestpermission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)


        binding.btn.setOnClickListener {
//            requestPermissionslauncher.launch(
//                arrayOf(
//                    permission.CAMERA,
//                    permission.CALL_PHONE,
//                    permission.READ_EXTERNAL_STORAGE
//                )
//            )

            requestpermission()
//            requestPermissionLauncher.launch(permission.CAMERA)
        }


    }

    val requestPermissionslauncher =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { permissions ->
            permissions.entries.forEach {
                when (it.key) {
                    permission.CAMERA -> {
                        if (it.value == false) {
                            finish()
                        }
                    }
                    else -> {
                        if (it.value == false) {
                            Toast.makeText(this, it.key, Toast.LENGTH_SHORT).show()
                        }
                    }

                }
            }
        }
    private val requestPermissionLauncher =
        registerForActivityResult(
            ActivityResultContracts.RequestPermission()
        ) { isGranted: Boolean ->
            if (isGranted) {
                Toast.makeText(this, "granted", Toast.LENGTH_SHORT).show()
            } else {
                Toast.makeText(this, "deny", Toast.LENGTH_SHORT).show()
            }
        }

    fun requestpermission() {
        val checkedcamera = ContextCompat.checkSelfPermission(this, permission.CAMERA)


        when {
            checkedcamera == PERMISSION_DENIED -> {
                Toast.makeText(this,"here",Toast.LENGTH_SHORT).show()
                requestPermissionLauncher.launch(permission.CAMERA)
            }
        }


    }


/*
//    private fun requestPermission() {
//        if (Build.VERSION.SDK_INT >= 28) {  // Androis 8.0 以上
//            // 判斷是否已取得授權
//            val hasPermission =
//                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)
//
//            if (hasPermission != PackageManager.PERMISSION_GRANTED) {  // 未取得授權
//
//                ActivityCompat.requestPermissions(  //要求權限
//                    this, arrayOf(
//                        Manifest.permission.CALL_PHONE,
//                        Manifest.permission.INTERNET
//                    ), 1
//                )
//            }
//        }
//        // 如果裝置版本是 Androis 6.0 以下，
//        // 或是裝置版本是6.0（包含）以上，使用者已經授權
//        // 允許執行程式
//    }
//
//    override fun onRequestPermissionsResult(
//        requestCode: Int,
//        permissions: Array<out String>,
//        grantResults: IntArray
//    ) {
//
//        when (requestCode) {
//
//        }
//        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
//    }

 */
}