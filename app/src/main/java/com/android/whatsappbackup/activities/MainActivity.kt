package com.android.whatsappbackup.activities

import android.content.Intent
import android.os.Build
import android.os.Bundle
import android.provider.Settings
import androidx.appcompat.app.AppCompatActivity
import androidx.cardview.widget.CardView
import com.android.whatsappbackup.R
import com.android.whatsappbackup.activities.home.*
import com.android.whatsappbackup.utils.Utils.checkPostNotificationPermission
import com.android.whatsappbackup.utils.Utils.isNotificationServiceEnabled
import com.android.whatsappbackup.utils.Utils.showToast
import com.android.whatsappbackup.utils.Utils.uiDefaultSettings

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        runOnUiThread {
            setContentView(R.layout.activity_main)
            uiDefaultSettings(this)
        }

        val bSpecificApp = findViewById<CardView>(R.id.bSpecificApp)
        val bAll = findViewById<CardView>(R.id.bAll)
        val bDeletedNotifications = findViewById<CardView>(R.id.bDeletedNotifications)
        val bAdvancedSearchActivity = findViewById<CardView>(R.id.bAdvancedSearchActivity)
        val bChat = findViewById<CardView>(R.id.bWhatsAppChat)
        val fbSettings = findViewById<CardView>(R.id.fbSettings)

        if (!isNotificationServiceEnabled(this)) {
            showToast(getString(R.string.ask_not_permission), this)

            val intent = Intent(Settings.ACTION_NOTIFICATION_LISTENER_SETTINGS)
                .addFlags(Intent.FLAG_ACTIVITY_NEW_TASK)

            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                intent.putExtra(Settings.EXTRA_APP_PACKAGE, this.packageName)
            }

            startActivity(intent)
        }

        checkPostNotificationPermission(this, this)

        bSpecificApp.setOnClickListener {
            val intent = Intent(this, SpecificAppNotificationsActivity::class.java)
            startActivity(intent)
        }

        bAll.setOnClickListener {
            val intent = Intent(this, AllNotificationsActivity::class.java)
            startActivity(intent)
        }

        bDeletedNotifications.setOnClickListener {
            val intent = Intent(this, DeletedNotificationsActivity::class.java)
            startActivity(intent)
        }

        bAdvancedSearchActivity.setOnClickListener {
            val intent = Intent(this, SearchActivity::class.java)
            startActivity(intent)
        }

        bChat.setOnClickListener {
            val intent = Intent(this, ChatActivity::class.java)
            startActivity(intent)
        }

        fbSettings.setOnClickListener {
            val intent = Intent(this, SettingsActivity::class.java)
            intent.addFlags(Intent.FLAG_ACTIVITY_NO_ANIMATION)
            startActivity(intent)
        }
    }
}
