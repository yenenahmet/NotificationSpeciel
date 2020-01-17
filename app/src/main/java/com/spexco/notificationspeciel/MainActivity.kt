package com.spexco.notificationspeciel

import android.app.NotificationChannel
import android.app.NotificationManager
import android.graphics.Color
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.AdapterView
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationManagerCompat
import kotlinx.android.synthetic.main.activity_main.*
import android.widget.Toast
import android.widget.ArrayAdapter


class MainActivity : AppCompatActivity() {



    private var notificationManager: NotificationManagerCompat? = null

    var country = arrayOf("India", "USA", "China", "Japan", "Other")

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        notificationManager = NotificationManagerCompat.from(this)
        createChallange()

        btnAdd.setOnClickListener {
            if(edtText.text.toString() == "1"){
                googleNot()
                controlText.text ="1"
            }else{
                controlText.text ="-1"
                Toast.makeText(this,"Notification Yollanmadı",Toast.LENGTH_LONG).show()
            }

        }

       val adapter= spinnerSetAdapter()

        spinner?.setOnItemSelectedListener(object: AdapterView.OnItemSelectedListener{
            override fun onNothingSelected(parent: AdapterView<*>?) {

            }

            override fun onItemSelected(
                parent: AdapterView<*>?,
                view: View?,
                position: Int,
                id: Long
            ) {
                selectedSpinnerItem.text = "selected ${adapter.getItem(position)}"
            }

        })

    }


    private fun notification() {

        val title1 = "Title 1"
        val message1 = "Message 1"
        val title2 = "Title 2"
        val message2 = "Message 2"

       /* val notification1 = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title1)
            .setContentText(message1)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setGroup("example_group")
            .build()

        val notification2 = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setContentTitle(title2)
            .setContentText(message2)
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setGroup("example_group")
            .build()*/

        val summaryNotification = NotificationCompat.Builder(this, "1")
            .setSmallIcon(R.drawable.ic_launcher_background)
            .setStyle(
                NotificationCompat.InboxStyle()
                    .addLine("$title2 $message2")
                    .addLine("$title1 $message1")
                    .setBigContentTitle("2 new messages")
                    //.setSummaryText("user@example.com")
            )
            .setPriority(NotificationCompat.PRIORITY_LOW)
            .setGroup("example_group")
            .setGroupAlertBehavior(NotificationCompat.GROUP_ALERT_CHILDREN)
            .setGroupSummary(true)
            .build()

        //SystemClock.sleep(2000)
       // notificationManager?.notify(2, notification1)
       // SystemClock.sleep(2000)
       // notificationManager?.notify(3, notification2)
      //  SystemClock.sleep(2000)
        notificationManager?.notify(4, summaryNotification)
    }

    private fun createChallange(){
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val adminChannel =
                NotificationChannel("1", "Name", NotificationManager.IMPORTANCE_HIGH)
            adminChannel.description = "Desc"
            adminChannel.enableLights(true)
            adminChannel.lightColor = Color.RED
            adminChannel.enableVibration(true)
            notificationManager?.createNotificationChannel(adminChannel)
        }
    }

    private  fun googleNot(){
        //use constant ID for notification used as group summary
        val SUMMARY_ID = 0
        val GROUP_KEY_WORK_EMAIL = "com.android.example.WORK_EMAIL"
        val group2 = "2"
        val newMessageNotification1 = NotificationCompat.Builder(this@MainActivity, "1")
            .setSmallIcon(R.mipmap.johndoe)
            .setContentTitle("1 email")
            .setContentText("You will not believe...")
            .setGroup(GROUP_KEY_WORK_EMAIL)
            .build()

        val newMessageNotification2 = NotificationCompat.Builder(this@MainActivity, "1")
            .setSmallIcon(R.mipmap.johndoe)
            .setContentTitle("2 email")
            .setContentText("Please join us to celebrate the...")
            .setGroup(GROUP_KEY_WORK_EMAIL)
            .build()

        val newMessageNotificationA1 = NotificationCompat.Builder(this@MainActivity, "1")
            .setSmallIcon(R.mipmap.johndoe)
            .setContentTitle(" A 1 mail")
            .setContentText("Please join us to celebrate the...")
            .setGroup(group2)
            .build()

        val summaryNotification = NotificationCompat.Builder(this@MainActivity, "1")
            .setContentTitle("özet")
            //set content text to support devices running API level < 24
            .setContentText("Two new messages")
            .setSmallIcon(R.mipmap.johndoe)
            //build summary info into InboxStyle template
            .setStyle(NotificationCompat.InboxStyle()
                .addLine("Alex Faarborg Check this out")
                .addLine("Jeff Chang Launch Party")
                .setBigContentTitle("2 new messages")
                .setSummaryText("janedoe@example.com"))
            //specify which group this notification belongs to
            .setGroup(GROUP_KEY_WORK_EMAIL)
            //set this notification as the summary for the group
            .setGroupSummary(true)
            .build()

        NotificationManagerCompat.from(this).apply {
            notify(1, newMessageNotification1)
            notify(2, newMessageNotification2)
            notify(SUMMARY_ID, summaryNotification)
        }
    }

    private fun spinnerSetAdapter() :ArrayAdapter<String>{
        val aa = ArrayAdapter(this, android.R.layout.simple_spinner_item, country)
        aa.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item)
        spinner.setAdapter(aa)
        return aa
    }


}
