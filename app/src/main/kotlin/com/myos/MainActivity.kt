package com.myos

import android.app.role.RoleManager
import android.os.Build
import android.os.Bundle
import android.widget.Button
import android.widget.Toast
import androidx.activity.result.contract.ActivityResultContracts
import androidx.appcompat.app.AppCompatActivity

class MainActivity : AppCompatActivity() {

    private val requestRoleLauncher = registerForActivityResult(
        ActivityResultContracts.StartActivityForResult()
    ) { result ->
        if (result.resultCode == RESULT_OK) {
            Toast.makeText(this, "Role granted successfully!", Toast.LENGTH_SHORT).show()
        } else {
            Toast.makeText(this, "Role request denied or cancelled.", Toast.LENGTH_SHORT).show()
        }
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnSetHome = findViewById<Button>(R.id.btnSetHome)
        val btnSetPhone = findViewById<Button>(R.id.btnSetPhone)
        val btnSetSms = findViewById<Button>(R.id.btnSetSms)

        btnSetHome.setOnClickListener {
            requestRole(RoleManager.ROLE_HOME)
        }

        btnSetPhone.setOnClickListener {
            requestRole(RoleManager.ROLE_DIALER)
        }

        btnSetSms.setOnClickListener {
            requestRole(RoleManager.ROLE_SMS)
        }
    }

    private fun requestRole(roleName: String) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
            val roleManager = getSystemService(RoleManager::class.java)
            if (roleManager != null && roleManager.isRoleAvailable(roleName)) {
                if (roleManager.isRoleHeld(roleName)) {
                    Toast.makeText(this, "Already set as default!", Toast.LENGTH_SHORT).show()
                } else {
                    val intent = roleManager.createRequestRoleIntent(roleName)
                    requestRoleLauncher.launch(intent)
                }
            } else {
                Toast.makeText(this, "Role not available on this device.", Toast.LENGTH_SHORT).show()
            }
        } else {
            Toast.makeText(this, "RoleManager requires Android 10 (API 29) or higher.", Toast.LENGTH_SHORT).show()
        }
    }
}
