package com.aslnstbk.telegram.ui.objects

import android.view.View
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import androidx.fragment.app.Fragment
import com.aslnstbk.telegram.R
import com.aslnstbk.telegram.ui.fragments.*
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
    private lateinit var mHeader: AccountHeader

    fun create(){
        createHeader()
        createDrawer()
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
                .withActivity(activity)
                .withHeaderBackground(R.drawable.drawer_menu_header)
                .addProfiles(
                        ProfileDrawerItem()
                                .withName("Asylzhan Seytbek")
                                .withEmail("+77475762101")
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
                        setPrimaryDrawerItem(100, "Создать группу", R.drawable.ic_menu_create_groups),
                        setPrimaryDrawerItem(101, "Создать секретный чат", R.drawable.ic_menu_secret_chat),
                        setPrimaryDrawerItem(102, "Создать канал", R.drawable.ic_menu_create_channel),
                        setPrimaryDrawerItem(103, "Контакты", R.drawable.ic_menu_contacts),
                        setPrimaryDrawerItem(104, "Звонки", R.drawable.ic_menu_phone),
                        setPrimaryDrawerItem(105, "Избранное", R.drawable.ic_menu_favorites),
                        setPrimaryDrawerItem(106, "Настройки", R.drawable.ic_menu_settings),
                        DividerDrawerItem(),
                        setPrimaryDrawerItem(107, "Пригласить друзей", R.drawable.ic_menu_invate),
                        setPrimaryDrawerItem(108, "Вопросы о телеграм", R.drawable.ic_menu_help)
                ).withOnDrawerItemClickListener(object: Drawer.OnDrawerItemClickListener {
                    override fun onItemClick(view: View?, position: Int, drawerItem: IDrawerItem<*>): Boolean {
                        Toast.makeText(activity, position.toString(), Toast.LENGTH_SHORT).show()
                        replaceFragment(position)
                        return false
                    }
                })
                .build()
    }

    private fun setPrimaryDrawerItem(id: Long, name: String, icon: Int): PrimaryDrawerItem {
        return PrimaryDrawerItem()
                .withIdentifier(id)
                .withIconTintingEnabled(true)
                .withName(name)
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