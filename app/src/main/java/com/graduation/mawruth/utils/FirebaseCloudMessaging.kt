package com.graduation.mawruth.utils

import android.app.Notification
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.util.Log
import androidx.annotation.RequiresApi
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import com.graduation.mawruth.R
import com.graduation.mawruth.ui.museumDetails.MuseumDetailsActivity


class FirebaseCloudMessaging : FirebaseMessagingService() {
    override fun onNewToken(token: String) {
        super.onNewToken(token)
        Log.d("token", token)

    }

    @RequiresApi(Build.VERSION_CODES.O)
    override fun onMessageReceived(message: RemoteMessage) {
        super.onMessageReceived(message)
        sendNotification(message.notification?.body.toString())
    }

    @RequiresApi(Build.VERSION_CODES.O)
    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, MuseumDetailsActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0, intent, PendingIntent.FLAG_IMMUTABLE)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder: Notification.Builder = Notification.Builder(this, "Main")
            .setSmallIcon(R.drawable.logo)
            .setContentTitle("Firebase Push Notification")
            .setContentText(messageBody)
            .setSound(defaultSoundUri)
            .setContentIntent(pendingIntent)
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.notify(0, notificationBuilder.build())
    }
}