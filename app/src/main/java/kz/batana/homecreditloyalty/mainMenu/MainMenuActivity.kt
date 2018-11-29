package kz.batana.homecreditloyalty.mainMenu

import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.support.design.widget.NavigationView
import android.support.v4.app.Fragment
import android.support.v4.app.FragmentManager
import android.support.v4.view.GravityCompat
import android.support.v7.app.ActionBarDrawerToggle
import android.support.v7.app.AlertDialog
import android.support.v7.app.AppCompatActivity
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import com.google.firebase.messaging.FirebaseMessaging
import kotlinx.android.synthetic.main.activity_main_menu.*
import kotlinx.android.synthetic.main.app_bar_main_menu.*
import kotlinx.android.synthetic.main.nav_header_main_menu.view.*
import kz.batana.homecreditloyalty.R
import kz.batana.homecreditloyalty.auth.LoginActivity
import kz.batana.homecreditloyalty.core.util.Logger
import kz.batana.homecreditloyalty.entity.Customer
import kz.batana.homecreditloyalty.entity.Task
import kz.batana.homecreditloyalty.history.HistoryFragment
import kz.batana.homecreditloyalty.report.ReportFragment
import kz.batana.homecreditloyalty.service.ServiceFragment
import kz.batana.homecreditloyalty.task.MainFragment
import kz.batana.homecreditloyalty.tasks_tabs.TasksBaseFragment
import org.koin.android.ext.android.inject


class MainMenuActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    var currentFragment: Fragment? = null
    var fragmentManager: FragmentManager = supportFragmentManager

    private val sharedPref: SharedPreferences by inject()
    companion object {
        var user:Customer?=null
        var tasks:ArrayList<Task>?=null

    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main_menu)
        FirebaseMessaging.getInstance().subscribeToTopic("task").addOnCompleteListener {
            if (it.isSuccessful){
                Log.d("subscription","SUCCESS")
            }
        }
        setSupportActionBar(toolbar)

//        var intent = intent
        if(MainMenuActivity.user == null){
            user = intent.getSerializableExtra("user") as Customer
        }else{
            user = MainMenuActivity.user
        }

        toolbar.title = "Главная"

        var mainFragment = MainFragment()
        var headerView = nav_view.getHeaderView(0)
        Logger.msg(user)
        headerView.textViewUserName.text = user?.name
        headerView.bonusTextView.text = user?.current_points.toString()
        headerView.textViewUserEmail.text = user?.email

        fragmentManager.beginTransaction().add(R.id.content, mainFragment).commit()
        val toggle = ActionBarDrawerToggle(
                this, drawer_layout, toolbar, R.string.navigation_drawer_open, R.string.navigation_drawer_close
        )
        drawer_layout.addDrawerListener(toggle)
        toggle.syncState()


        nav_view.setNavigationItemSelectedListener(this)

    }

    fun updatePoints(points: Int) {
        var headerView = nav_view.getHeaderView(0)
        headerView.bonusTextView.text = points.toString()
    }

    override fun onBackPressed() {
        if (drawer_layout.isDrawerOpen(GravityCompat.START)) {
            drawer_layout.closeDrawer(GravityCompat.START)
        } else {
            super.onBackPressed()
        }
    }

    override fun onResume() {
        super.onResume()
        var headerView = nav_view.getHeaderView(0)
        headerView.bonusTextView.text = user?.current_points.toString()
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {

        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        when (item.itemId) {
            R.id.action_settings -> {
                val intent = Intent(this, LoginActivity::class.java)
                startActivity(intent)
                finish()
                return true
            }
            else -> return super.onOptionsItemSelected(item)
        }
    }

    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.nav_news -> {
                toolbar.title = "Главная"
                currentFragment = MainFragment()
            }
            R.id.nav_report -> {
                toolbar.title = "Сообщить"
                currentFragment = ReportFragment()
            }
            R.id.nav_tasks -> {
                toolbar.title = "Задачи"
                currentFragment = TasksBaseFragment()
            }
            R.id.nav_history -> {
                toolbar.title = "История"
                currentFragment = HistoryFragment()
            }
            R.id.nav_payments -> {
                toolbar.title = "Платежи"
                currentFragment = ServiceFragment()
            }
            R.id.nav_about -> {
                val builder = AlertDialog.Builder(this)
                builder.setMessage("The app was developed by: \nNursultan Almakhanov\nAskhat Telzhanov\nAbylai Edilbayev\nAyan Kurmanbay" +
                        "\nHackday Almaty 2018")
                        .setPositiveButton("Good!", { dialog, id ->})
                // Create the AlertDialog object and return it
                builder.create()
                drawer_layout.closeDrawer(GravityCompat.START)
                builder.show()
                return true
            }
        }
        fragmentManager.beginTransaction().replace(R.id.content, currentFragment!!).commit()
        drawer_layout.closeDrawer(GravityCompat.START)

        return true
    }

}