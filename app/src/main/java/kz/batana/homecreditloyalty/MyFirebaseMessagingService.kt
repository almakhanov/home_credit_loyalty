package kz.batana.homecreditloyalty

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.media.RingtoneManager
import android.os.Build
import android.support.v4.app.NotificationCompat
import android.util.Log
import com.google.firebase.messaging.FirebaseMessagingService
import com.google.firebase.messaging.RemoteMessage
import kz.batana.homecreditloyalty.auth.LoginActivity
import com.google.firebase.messaging.RemoteMessage
import kz.batana.homecreditloyalty.tasks_tabs.TasksService
import org.koin.android.ext.android.inject


class MyFirebaseMessagingService: FirebaseMessagingService() {
    private val taskService: TasksService by inject()
    override fun onNewToken(token: String?) {
        super.onNewToken(token)

        // If you want to send messages to this application instance or
        // manage this apps subscriptions on the server side, send the
        // Instance ID token to your app server.
        sendRegistrationToServer(token)
    }

    private fun sendRegistrationToServer(token:String?){

    }

    override fun onMessageReceived(remoteMessage: RemoteMessage?) {

        Log.d("From:", remoteMessage?.from)

        if (remoteMessage!!.data.size>0){
            Log.d("Message data", remoteMessage.data.toString())

        }
        if (remoteMessage.notification != null) {
            Log.d("message notification", "Message Notification Body: " + remoteMessage.notification?.body)
        }

//        taskService.addTask(remoteMessage.notification!!.title!!,remoteMessage.notification!!.body!!)
//                .subscribeOn(Schedulers.io())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe({
//                    Log.d("RESPONSE","OK")
//                },{
//
//                })
        sendNotification(remoteMessage.notification!!.title!!)

    }

    private fun sendNotification(messageBody: String) {
        val intent = Intent(this, LoginActivity::class.java)
        intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP)
        val pendingIntent = PendingIntent.getActivity(this, 0 /* Request code */, intent,
                PendingIntent.FLAG_ONE_SHOT)

        val channelId = getString(R.string.default_notification_channel_id)
        val defaultSoundUri = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_NOTIFICATION)
        val notificationBuilder = NotificationCompat.Builder(this, channelId)
                .setSmallIcon(R.drawable.ic_loyalty_black_24dp)
                .setContentTitle(getString(R.string.fcm_message))
                .setContentText(messageBody)
                .setAutoCancel(true)
                .setSound(defaultSoundUri)
                .setContentIntent(pendingIntent)

        val notificationManager = getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        // Since android Oreo notification channel is needed.
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val channel = NotificationChannel(channelId,
                    "Channel human readable title",
                    NotificationManager.IMPORTANCE_DEFAULT)
            notificationManager.createNotificationChannel(channel)
        }

        notificationManager.notify(0 /* ID of notification */, notificationBuilder.build())
    }
}