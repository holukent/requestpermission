package com.example.requestpermission

import android.app.Activity
import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.TextView

class MainActivity2 : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main2)

        val page1 = intent.getStringExtra("page1")
        findViewById<TextView>(R.id.main2Text).text = page1

        val btnprepage = findViewById<Button>(R.id.btnprepage)
        btnprepage.setOnClickListener {
            val intent = Intent().apply { putExtra("result","prenext") }
            setResult(Activity.RESULT_OK,intent)
            finish()
        }
    }
}