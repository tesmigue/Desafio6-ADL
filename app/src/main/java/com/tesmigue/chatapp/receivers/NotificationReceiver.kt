package com.tesmigue.chatapp.receivers

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import com.tesmigue.chatapp.presentation.ui.chat.ChatActivity

class NotificationReceiver : BroadcastReceiver() {
    override fun onReceive(context: Context, intent: Intent) {
        when (intent.action) {
            "com.tesmigue.chatapp.NOTIFICATION_CLICKED" -> {
                val salaId = intent.getStringExtra("salaId")
                val chatIntent = Intent(context, ChatActivity::class.java).apply {
                    putExtra("salaId", salaId)
                    flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TOP
                }
                context.startActivity(chatIntent)
            }
        }
    }
}