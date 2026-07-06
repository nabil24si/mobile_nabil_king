package com.example.nabil_king.util

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.util.Log

class ReminderReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        Log.d("ReminderReceiver", "Alarm received!")
        val title = intent.getStringExtra("TITLE") ?: "Pengingat Sengketa"
        val message = intent.getStringExtra("MESSAGE") ?: "Ada sengketa yang perlu segera diproses."
        
        val notificationHelper = NotificationHelper(context)
        notificationHelper.sendNotification(title, message)
    }
}