package com.aslnstbk.telegram

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import androidx.fragment.app.Fragment
import com.aslnstbk.telegram.databinding.ActivityMainBinding
import com.aslnstbk.telegram.ui.*
import com.mikepenz.materialdrawer.AccountHeader
import com.mikepenz.materialdrawer.AccountHeaderBuilder
import com.mikepenz.materialdrawer.Drawer
import com.mikepenz.materialdrawer.DrawerBuilder
import com.mikepenz.materialdrawer.model.DividerDrawerItem
import com.mikepenz.materialdrawer.model.PrimaryDrawerItem
import com.mikepenz.materialdrawer.model.ProfileDrawerItem
import com.mikepenz.materialdrawer.model.interfaces.IDrawerItem

class MainActivity : AppCompatActivity() {

    private lateinit var mBinding: ActivityMainBinding
    private lateinit var mDrawer: Drawer
    private lateinit var mHeader: AccountHeader
    private lateinit var mToolbar: androidx.appcompat.widget.Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunc()
    }

    private fun initFunc() {
        setSupportActionBar(mToolbar)

        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, ChatsFragment())
            .commit()

        createHeader()
        createDrawer()
    }

    private fun createDrawer() {
        mDrawer = DrawerBuilder()
                .withActivity(this)
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
                        Toast.makeText(applicationContext, position.toString(), Toast.LENGTH_SHORT).show()

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

                        return false
                    }
                })
                .build()
    }

    private fun checkoutFragment(Fragment: Fragment){

        supportFragmentManager.beginTransaction()
            .addToBackStack(null)
            .replace(R.id.dataContainer, Fragment)
            .commit()
    }

    private fun setPrimaryDrawerItem(id: Long, name: String, icon: Int): PrimaryDrawerItem {
        return PrimaryDrawerItem()
                .withIdentifier(id)
                .withIconTintingEnabled(true)
                .withName(name)
                .withSelectable(false)
                .withIcon(icon)
    }

    private fun createHeader() {
        mHeader = AccountHeaderBuilder()
                .withActivity(this)
                .withHeaderBackground(R.drawable.drawer_menu_header)
                .addProfiles(
                        ProfileDrawerItem()
                                .withName("Asylzhan Seytbek")
                                .withEmail("+77475762101")
                ).build()

    }

    private fun initFields() {
        mToolbar = mBinding.mainToolbar
    }
}