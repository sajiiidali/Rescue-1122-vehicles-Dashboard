package com.sajiiidali.rescue1122.vehicle.dashboard

import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.net.ConnectivityManager
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.Looper
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.splashscreen.SplashScreen.Companion.installSplashScreen
import androidx.core.view.GravityCompat
import androidx.drawerlayout.widget.DrawerLayout
import androidx.navigation.NavController
import androidx.navigation.findNavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.*
import androidx.preference.PreferenceManager
import com.google.android.material.bottomnavigation.BottomNavigationView
import com.google.android.material.dialog.MaterialAlertDialogBuilder
import com.google.android.material.navigation.NavigationView
import com.sajiiidali.rescue1122.vehicle.dashboard.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), NavigationView.OnNavigationItemSelectedListener {
    private lateinit var appBarConfiguration: AppBarConfiguration
    private lateinit var binding: ActivityMainBinding
    lateinit var navController: NavController
    lateinit var drawerLayout: DrawerLayout

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        var keepSplashOnScreen = true
        val delay = 2000L
        installSplashScreen().setKeepOnScreenCondition { keepSplashOnScreen }
        Handler(Looper.getMainLooper()).postDelayed({ keepSplashOnScreen = false }, delay)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        setSupportActionBar(binding.appBarMain.toolbar)

        drawerLayout = binding.drawerLayout
        val navView: NavigationView = binding.navView
        val bottomNavigation : BottomNavigationView = findViewById(R.id.bottom_navigation_id)
        val navHost : NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController = navHost.navController

        if (!isNetworkConnected()){
            showErrorDialog()
        }

        appBarConfiguration = AppBarConfiguration(setOf(R.id.MainDashBoard), drawerLayout)
        setupActionBarWithNavController(navController, appBarConfiguration)
        navView.setupWithNavController(navController)
        navView.setNavigationItemSelectedListener(this)
        NavigationUI.setupWithNavController(bottomNavigation, navController)

        navController.addOnDestinationChangedListener { _, destination, arguments ->
            when (destination.id) {
                R.id.showEmergencyReports->{
                    bottomNavigation.visibility = View.GONE
                    supportActionBar?.hide()
                }
                R.id.vehicles3,R.id.selectEmployees->{
                    supportActionBar?.hide()
                }
                else -> {
                    bottomNavigation.visibility = View.VISIBLE
                    supportActionBar?.show()
                }
            }
        }
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.reset -> {
                resetUserNamePassword()
                return true
            }
        }
        return super.onOptionsItemSelected(item)
    }

    private fun resetUserNamePassword() {
        MaterialAlertDialogBuilder(this,R.style.MaterialAlertDialog).setCancelable(false)
            .setTitle(resources.getString(R.string.resetTitle))
             .setNegativeButton(resources.getString(R.string.Cancel)) { dialog, _ ->
                 dialog.dismiss()
             }
            .setPositiveButton(resources.getString(android.R.string.ok)) { dialog, _ ->
                val settingPreferences = PreferenceManager.getDefaultSharedPreferences(this)
                val editor = settingPreferences.edit()
                editor.putString("userName", null)
                editor.putString("password", null)
                editor.putString("location", null)
                editor.apply()
                dialog.dismiss()
                navController.navigate(R.id.saveUserNamePassword)
            }
            .show()
    }

    override fun onSupportNavigateUp(): Boolean {
        val navController = findNavController(R.id.fragmentContainer)
        return navController.navigateUp(appBarConfiguration) || super.onSupportNavigateUp()
    }
    override fun onNavigationItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.share->{
                share()
            }
            R.id.rateus->{
                rateUs()
            }
            R.id.privacyPolicy->{
                privacyPolicy()
            }
            R.id.checkUpdate->{
                rateUs()
            }
            R.id.aboutUs->{
                aboutUs()
            }
        }
        drawerLayout.closeDrawer(GravityCompat.START)
        return true
    }

    private fun rateUs() {
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("market://details?id=$packageName")
            )
        )
        startActivity(
            Intent(
                Intent.ACTION_VIEW,
                Uri.parse("http://play.google.com/store/apps/details?id=$packageName")
            )
        )
    }

    private fun isNetworkConnected(): Boolean {
        val cm = this.getSystemService(Context.CONNECTIVITY_SERVICE) as ConnectivityManager
        return cm.activeNetworkInfo != null
    }

    private fun showErrorDialog() {

        MaterialAlertDialogBuilder(this,R.style.MaterialAlertDialog).setCancelable(false)
            .setTitle(resources.getString(R.string.internet_error_title))
            .setMessage(resources.getString(R.string.internet_error_message))
            /* .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                 // Respond to neutral button press
             }
             .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                 // Respond to negative button press
             }*/
            .setPositiveButton(resources.getString(android.R.string.ok)) { dialog, which ->
                if (isNetworkConnected()){
                    dialog.dismiss()
                }else{
                    dialog.dismiss()
                    showErrorDialog()
                }
            }
            .show()
    }

    private fun share() {
        val shareIntent = Intent(Intent.ACTION_SEND)
        shareIntent.type = "text/plain"
        shareIntent.putExtra(Intent.EXTRA_SUBJECT, "Subject Here")
        shareIntent.putExtra(
            Intent.EXTRA_TEXT,
            "https://play.google.com/store/apps/details?id=$packageName"
        )
        startActivity(Intent.createChooser(shareIntent, "Share Via"))
    }

    private fun aboutUs() {

        MaterialAlertDialogBuilder(this,R.style.MaterialAlertDialog)
            .setIcon(R.drawable.company_logo_t)
            .setTitle(resources.getString(R.string.companyName))
            .setMessage(resources.getString(R.string.aboutUS))
            /* .setNeutralButton(resources.getString(R.string.cancel)) { dialog, which ->
                 // Respond to neutral button press
             }
             .setNegativeButton(resources.getString(R.string.decline)) { dialog, which ->
                 // Respond to negative button press
             }*/
            .setPositiveButton(resources.getString(android.R.string.ok)) { dialog, which ->
                if (isNetworkConnected()){
                    dialog.dismiss()
                }else{
                    dialog.dismiss()
                    showErrorDialog()
                }
            }
            .show()
    }

    private fun privacyPolicy() {
        val browserIntent = Intent(Intent.ACTION_VIEW, Uri.parse("https://rescue1122vehiclesdashboard.blogspot.com/"))
        startActivity(browserIntent)
    }

    /*private fun moreApps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://search?q=pub:Digital Finger Apps")
        startActivity(intent)
    }*/

}



