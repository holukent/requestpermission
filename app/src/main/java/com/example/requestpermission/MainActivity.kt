package com.example.requestpermission

import android.Manifest
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.widget.Toast
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
        }


        requestPermission()
        requestPer()
    }

    fun requestPer() {
        if (Build.VERSION.SDK_INT >= 28) {
            Toast.makeText(this, "${Build.VERSION.SDK_INT}", Toast.LENGTH_SHORT).show()
        }
    }

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

        when(requestCode) {
            1 ->
        }
        super.onRequestPermissionsResult(requestCode, permissions, grantResults)
    }
}