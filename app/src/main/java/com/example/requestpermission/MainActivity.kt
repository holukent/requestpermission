package com.example.requestpermission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.requestpermission.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {
//            requestPermission()

            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED ->
                    requestpermission.launch(Manifest.permission.CALL_PHONE)
            }
        }

    }

    /*Allow the system to manage the permission request code*/
    private val requestpermission:ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Toast.makeText(this, "HEHE", Toast.LENGTH_SHORT).show()
                finish()
            } else {

            }
        }
    val requestpermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) {

        }




    /*Manage the permission request code yourself*/
    private fun requestPermission() {
        if (Build.VERSION.SDK_INT >= 28) {  // Androis 6.0 以上
            // 判斷是否已取得授權
            val hasPermission =
                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)

            if (hasPermission != PackageManager.PERMISSION_GRANTED) {  // 未取得授權
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 2)
            }
        }
        // 如果裝置版本是 Androis 6.0 以下，
        // 或是裝置版本是6.0（包含）以上，使用者已經授權
        // 允許執行程式
    }

    override fun onRequestPermissionsResult(
        requestCode: Int,
        permissions: Array<out String>,
        grantResults: IntArray
    ) {

        when (requestCode) {
            2 -> Toast.makeText(this, "here", Toast.LENGTH_SHORT).show()
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}