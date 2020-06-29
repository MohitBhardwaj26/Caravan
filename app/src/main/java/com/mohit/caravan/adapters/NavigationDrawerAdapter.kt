package com.mohit.caravan.adapters

import android.content.Context
import android.support.v7.widget.RecyclerView
import android.view.ContextMenu
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.RelativeLayout
import android.widget.TextView
import com.mohit.caravan.R
import com.mohit.caravan.activites.MainActivity
import com.mohit.caravan.fragments.AboutUsFragment
import com.mohit.caravan.fragments.FavoriteFragment
import com.mohit.caravan.fragments.MainScreenFragment
import com.mohit.caravan.fragments.SettingFragment
import java.text.FieldPosition

class NavigationDrawerAdapter(_contentList:ArrayList<String>,_getImages:IntArray,_contex: Context): RecyclerView.Adapter<NavigationDrawerAdapter.NavVewHolder>() {

    var contentList: ArrayList<String>? = null
    var getImages: IntArray? = null
    var mContext: Context? = null

    init {
        this.contentList = _contentList
        this.getImages = _getImages
        this.mContext = _contex
    }

    override fun onBindViewHolder(holder: NavVewHolder, position: Int) {

        holder?.icon_GET?.setBackgroundResource(getImages?.get(position) as Int)
        holder?.text_GET?.setText(contentList?.get(position))
        holder?.contentHolder?.setOnClickListener({
            if (position == 0) {
                val mainScreenFragment = MainScreenFragment()
                (mContext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, mainScreenFragment)
                    .commit()
            } else if (position == 1) {
                val favoriteFragment = FavoriteFragment()
                (mContext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, favoriteFragment)
                    .commit()
            } else if (position == 2) {
                val settingFragment = SettingFragment()
                (mContext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, settingFragment)
                    .commit()
            } else {
                val aboutUsFragment = AboutUsFragment()
                (mContext as MainActivity).supportFragmentManager
                    .beginTransaction()
                    .replace(R.id.details_fragment, aboutUsFragment)
                    .commit()
            }
            MainActivity.Statified.drawerLayout?.closeDrawers()

        })
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): NavVewHolder {
        val view = LayoutInflater.from(parent.context).inflate(R.layout.row_custom_navigationdrawer, parent, false)
        return NavVewHolder(view)
    }


    override fun getItemCount():Int {
        return contentList?.size as Int
    }




    class NavVewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {

        var icon_GET: ImageView? = null
        var text_GET: TextView? = null
        var contentHolder: RelativeLayout? = null
        init {
            icon_GET =itemView?.findViewById(R.id.icon_navdrawer)
            text_GET =itemView?.findViewById(R.id.text_navdrawer)
            contentHolder =itemView?.findViewById(R.id.navdrawe_item_content_holder)

        }

    }


}