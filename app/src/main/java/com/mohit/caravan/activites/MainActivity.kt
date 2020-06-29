package com.mohit.caravan.activites

import android.app.Notification
import android.app.NotificationChannelGroup
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Bundle
import android.os.PersistableBundle
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v4.widget.DrawerLayout
import android.support.v7.app.AppCompatActivity
import android.support.v7.widget.DefaultItemAnimator
import android.support.v7.widget.LinearLayoutManager
import android.support.v7.widget.RecyclerView
import android.support.v7.widget.Toolbar
import com.mohit.caravan.fragments.MainScreenFragment
import com.mohit.caravan.R
import com.mohit.caravan.adapters.NavigationDrawerAdapter
import com.mohit.caravan.fragments.SongPlayingFragment
import kotlinx.android.synthetic.main.activity_main.*
import java.lang.Exception

class MainActivity : AppCompatActivity(){
    object  Statified{
        var drawerLayout: DrawerLayout? = null
        var notificationManager:NotificationManager?=null
    }

    var navigationDrawerIconsList:ArrayList<String> = arrayListOf()
    var images_of_navdrawer= intArrayOf(R.drawable.navigation_allsongs,R.drawable.navigation_favorites,
        R.drawable.navigation_settings,R.drawable.navigation_aboutus)
    var trackNotificationBuilder:Notification?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        navigationDrawerIconsList.add("All Songs")
        navigationDrawerIconsList.add("Favorite")
        navigationDrawerIconsList.add("Settings")
        navigationDrawerIconsList.add("About Us")

        val toolbar=findViewById<Toolbar>(R.id.toolbar)
        setSupportActionBar(toolbar)
        MainActivity.Statified.drawerLayout = findViewById(R.id.drawer_layout)
        val toggle=ActionBarDrawerToggle(this@MainActivity,MainActivity.Statified.drawerLayout,toolbar,
            R.string.navigation_drawer_open,
            R.string.navigation_drawer_close
        )
        MainActivity.Statified.drawerLayout?.addDrawerListener(toggle)
        toggle.syncState()
        val mainScreenFragment= MainScreenFragment()
        this.supportFragmentManager
            .beginTransaction()
            .add(R.id.details_fragment,mainScreenFragment,"MainScreenFragment")
            .commit()

        val _navigationAdapter = NavigationDrawerAdapter(navigationDrawerIconsList,images_of_navdrawer,this)
        _navigationAdapter.notifyDataSetChanged()

        val navigation_recycler_view=findViewById<RecyclerView>(R.id.navigation_recycler_view)
        navigation_recycler_view.layoutManager=LinearLayoutManager(this)
        navigation_recycler_view.itemAnimator=DefaultItemAnimator()
        navigation_recycler_view.adapter=_navigationAdapter
        navigation_recycler_view.setHasFixedSize(true)

        val intent=Intent(this@MainActivity,MainActivity::class.java)
        val pIntent=PendingIntent.getActivity(this@MainActivity,System.currentTimeMillis().toInt(),intent,0)
        trackNotificationBuilder=Notification.Builder(this)
            .setContentTitle("A track is playing in background")
            .setSmallIcon(R.drawable.ic_back_to_list)
            .setContentIntent(pIntent)
            .setOngoing(true)
            .setAutoCancel(true)
            .build()
         Statified.notificationManager=getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
    }
    override fun onStart() {
        super.onStart()
        try {
            Statified.notificationManager?.cancel(1507)

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onStop() {
        super.onStop()
        try {
            if (SongPlayingFragment.Statified.mediaPlayer?.isPlaying as Boolean){
                Statified.notificationManager?.notify(1507,trackNotificationBuilder)
            }

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

    override fun onResume() {
        super.onResume()
        try {
            Statified.notificationManager?.cancel(1507)

        }catch (e:Exception){
            e.printStackTrace()
        }
    }

}
