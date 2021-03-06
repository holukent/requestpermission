package com.example.requestpermission

import android.Manifest
import android.app.Activity
import android.content.Intent
import android.content.pm.PackageManager
import android.os.Build
import android.os.Bundle
import android.os.Environment
import android.widget.Toast
import androidx.activity.result.ActivityResultLauncher
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity
import androidx.core.app.ActivityCompat
import androidx.core.content.ContextCompat
import com.example.requestpermission.databinding.ActivityMainBinding
import java.io.File

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.btn.setOnClickListener {

//            requestPermission()

//            when {
//                ContextCompat.checkSelfPermission(
//                    this,
//                    Manifest.permission.CALL_PHONE
//                ) != PackageManager.PERMISSION_GRANTED ->
//                    requestpermission.launch(Manifest.permission.CALL_PHONE)
//            }
            when {
                ContextCompat.checkSelfPermission(
                    this,
                    Manifest.permission.CALL_PHONE
                ) != PackageManager.PERMISSION_GRANTED ->
                    requestpermissions.launch(arrayOf(Manifest.permission.CALL_PHONE))
            }
        }

        binding.btnnextpage.setOnClickListener {
            Toast.makeText(this,isExternalStorageReadable().toString(),Toast.LENGTH_SHORT).show()
            Toast.makeText(this,isExternalStorageWritable().toString(),Toast.LENGTH_SHORT).show()

            val externalStorageVolumes: Array<out File> =
                ContextCompat.getExternalFilesDirs(applicationContext, null)
            val primaryExternalStorage = externalStorageVolumes[0]




//            mActivityLancher.launch(
//                Intent(this, MainActivity2::class.java).putExtra(
//                    "page1",
//                    "page123"
//                )
//            )
        }
    }

    fun isExternalStorageWritable(): Boolean {
        return Environment.getExternalStorageState() == Environment.MEDIA_MOUNTED
    }
    fun isExternalStorageReadable(): Boolean {
        return Environment.getExternalStorageState() in
                setOf(Environment.MEDIA_MOUNTED, Environment.MEDIA_MOUNTED_READ_ONLY)
    }

    /*StartActivityForResult*/
    val mActivityLancher =
        registerForActivityResult(ActivityResultContracts.StartActivityForResult()) {
            if (it.resultCode == Activity.RESULT_OK)
                Toast.makeText(this, "${it.data?.getStringExtra("result")}", Toast.LENGTH_SHORT)
                    .show()
            else Toast.makeText(this, "no DATA", Toast.LENGTH_SHORT)
                .show()
        }

    /*Allow the system to manage the permission request code*/
    private val requestpermission: ActivityResultLauncher<String> =
        registerForActivityResult(ActivityResultContracts.RequestPermission()) { isGranted ->
            if (!isGranted) {
                Toast.makeText(this, "HEHE", Toast.LENGTH_SHORT).show()
                finish()
            } else {
            }
        }
    private val requestpermissions =
        registerForActivityResult(ActivityResultContracts.RequestMultiplePermissions()) { map ->
            map.entries.forEach {
                when (it.key) {
                    Manifest.permission.CALL_PHONE -> {
                        if (!it.value) finish()
                    }
                    else -> {
                    }
                }
            }
        }


    /*Manage the permission request code yourself*/
    private fun requestPermission1() {
        if (Build.VERSION.SDK_INT >= 30) {  // Androis 6.0 ??????
            // ???????????????????????????
            val hasPermission =
                ContextCompat.checkSelfPermission(this, Manifest.permission.CALL_PHONE)

            if (hasPermission != PackageManager.PERMISSION_GRANTED) {  // ???????????????
                ActivityCompat.requestPermissions(this, arrayOf(Manifest.permission.CALL_PHONE), 2)
            }
        }
        // ????????????????????? Androis 6.0 ?????????
        // ?????????????????????6.0??????????????????????????????????????????
        // ??????????????????
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