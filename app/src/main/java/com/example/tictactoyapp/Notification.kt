package com.example.tictactoyapp

import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import android.support.v4.app.NotificationCompat


class NotificationClass () {
val NOTIFY_TAG="NotificationTAG"

    fun NotifyUser(context: Context,message:String,number:Int ){
        val intent = Intent(context,MainActivity::class.java)

        val builder = NotificationCompat.Builder(context)
                .setContentTitle("New Request")
                .setContentText(message)
                .setNumber(number)
                .setSmallIcon(R.mipmap.ic_launcher)
                .setContentIntent(PendingIntent.getActivity(context,
                        0,intent,PendingIntent.FLAG_UPDATE_CURRENT))
                .setAutoCancel(true)

        val nm = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager

        if(Build.VERSION.SDK_INT>Build.VERSION_CODES.ECLAIR)
        {
            nm.notify(NOTIFY_TAG,0,builder.build())
        }
        else
        {
            nm.notify(NOTIFY_TAG.hashCode(),builder.build())
        }

    }

}