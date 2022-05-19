package com.sajiiidali.rescue1122.vehicle.dashboard

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AlertDialog
import androidx.navigation.NavController
import androidx.navigation.fragment.NavHostFragment
import androidx.navigation.ui.NavigationUI
import java.util.*

class MainActivity : AppCompatActivity() {
    private lateinit var navController: NavController

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        Objects.requireNonNull((this).supportActionBar!!).setTitle(R.string.app_name)
        Objects.requireNonNull((this).supportActionBar!!).show()

        val navHost : NavHostFragment = supportFragmentManager.findFragmentById(R.id.fragmentContainer) as NavHostFragment
        navController= navHost.navController
        NavigationUI.setupActionBarWithNavController(this,navController)

    }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.my_menu, menu)
        return true
    }
    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when (item.itemId) {
            R.id.aboutUs -> {
                aboutUs()
                return true
            }
            R.id.settings ->{
            navController.navigate(R.id.settingsFragment)
            }
            R.id.share -> {
                share()
                return true
            }
            R.id.rateus -> {
                rateUs()
                return true
            }
        }

        return super.onOptionsItemSelected(item)
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
        val dialog : AlertDialog.Builder = AlertDialog.Builder(this)
        dialog.setIcon(R.drawable.company_logo)
        dialog.setTitle(R.string.companyName)
        dialog.setMessage(R.string.aboutUS)
        dialog.setPositiveButton(android.R.string.ok){ myDialog,
                                                     _-> myDialog.dismiss()
        }.show()
    }

    /*private fun moreApps() {
        val intent = Intent(Intent.ACTION_VIEW)
        intent.data = Uri.parse("market://search?q=pub:Digital Finger Apps")
        startActivity(intent)
    }*/
}



