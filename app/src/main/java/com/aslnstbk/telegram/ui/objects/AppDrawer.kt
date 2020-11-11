package com.aslnstbk.telegram.ui.objects

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.drawerlayout.widget.DrawerLayout
import androidx.fragment.app.Fragment
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.ui.fragments.*
import com.aslnstbk.telegram.utils.USER
import com.aslnstbk.telegram.utils.initFirebase
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class AppDrawer(val activity: AppCompatActivity, val mToolbar: Toolbar) {

    private lateinit var mDrawer: Drawer
    private lateinit var mDrawerLayout: DrawerLayout
    private lateinit var mHeader: AccountHeader

    fun create(){
        initFirebase()
        createHeader()
        createDrawer()
        mDrawerLayout = mDrawer.drawerLayout
    }

    fun disableDrawer(){
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = false
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(true)
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_LOCKED_CLOSED)
        mToolbar.setNavigationOnClickListener {
            activity.supportFragmentManager.popBackStack()
        }
    }

    fun enableDrawer(){
        activity.supportActionBar?.setDisplayHomeAsUpEnabled(false)
        mDrawer.actionBarDrawerToggle?.isDrawerIndicatorEnabled = true
        mDrawerLayout.setDrawerLockMode(DrawerLayout.LOCK_MODE_UNLOCKED)
        mToolbar.setNavigationOnClickListener {
            mDrawer.openDrawer()
        }
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.drawer_menu_header)
                .addProfiles(
                        ProfileDrawerItem()
                            .withName(USER.fullname)
                            .withEmail(USER.phone)
                ).build()
    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder()
                .withActivity(activity)
                .withToolbar(mToolbar)
                .withActionBarDrawerToggle(true)
                .withSelectedItem(-1)
                .withAccountHeader(mHeader)
                .addDrawerItems(
                        setPrimaryDrawerItem(100, R.string.drawer_menu_create_group, R.drawable.ic_menu_create_groups),
                        setPrimaryDrawerItem(101, R.string.drawer_menu_create_secret_chat, R.drawable.ic_menu_secret_chat),
                        setPrimaryDrawerItem(102, R.string.drawer_menu_create_channel, R.drawable.ic_menu_create_channel),
                        setPrimaryDrawerItem(103, R.string.drawer_menu_contacts, R.drawable.ic_menu_contacts),
                        setPrimaryDrawerItem(104, R.string.drawer_menu_calls, R.drawable.ic_menu_phone),
                        setPrimaryDrawerItem(105, R.string.drawer_menu_favorites, R.drawable.ic_menu_favorites),
                        setPrimaryDrawerItem(106, R.string.drawer_menu_settings, R.drawable.ic_menu_settings),
                        DividerDrawerItem(),
                        setPrimaryDrawerItem(107, R.string.drawer_menu_invate_friends, R.drawable.ic_menu_invate),
                        setPrimaryDrawerItem(108, R.string.drawer_menu_help, R.drawable.ic_menu_help)
                ).withOnDrawerItemClickListener(object: Drawer.OnDrawerItemClickListener {
                    override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean {
                        Toast.makeText(activity, position.toString(), Toast.LENGTH_SHORT).show()
                        replaceFragment(position)
                        return false
                    }
                })
                .build()
    }

    private fun setPrimaryDrawerItem(id: Long, resourcesName: Int, icon: Int): PrimaryDrawerItem {
        return PrimaryDrawerItem()
                .withIdentifier(id)
                .withIconTintingEnabled(true)
                .withName(activity.getString(resourcesName))
                .withSelectable(false)
                .withIcon(icon)
    }

    private fun replaceFragment(position: Int){
        when(position){
            1 -> checkoutFragment(CreateGroupFragment())
            2 -> checkoutFragment(CreateSecretChatFragment())
            3 -> checkoutFragment(CreateChannelFragment())
            4 -> checkoutFragment(ContactsFragment())
            5 -> checkoutFragment(CallsFragment())
            6 -> checkoutFragment(FavoritesFragment())
            7 -> checkoutFragment(SettingsFragment())
            8 -> checkoutFragment(InvateFriendsFragment())
            9 -> checkoutFragment(HelpFragment())
        }
    }

    private fun checkoutFragment(Fragment: Fragment){
        activity.supportFragmentManager.beginTransaction()
                .addToBackStack(null)
                .replace(R.id.dataContainer, Fragment)
                .commit()
    }
}