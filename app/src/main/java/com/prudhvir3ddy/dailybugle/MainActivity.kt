package com.prudhvir3ddy.dailybugle

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.prudhvir3ddy.dailybugle.ui.SettingsActivity

/** MainActivity
 * This Activity serves as a placeholder for
 * Home, search and saved screens
 */
class MainActivity : AppCompatActivity() {

  override fun onCreate(savedInstanceState: Bundle?) {
    (application as MyApplication).appComponent.inject(this)
    super.onCreate(savedInstanceState)
    setContentView(R.layout.activity_main)

  }

  /**
   * onCreateOptionsMenu
   * to inflate the three dotted menu in top right of the screen
   */
  override fun onCreateOptionsMenu(menu: Menu?): Boolean {
    menuInflater.inflate(R.menu.main_menu, menu)
    return true
  }

  /**
   * onOptionsItemSelected
   * to handle different menu items inside the menu
   * currently we only have one menu item on clicking it
   * we are taking user to settings screen
   */
  override fun onOptionsItemSelected(item: MenuItem): Boolean {

    if (item.itemId == R.id.menu_item_settings) {
      startActivity(Intent(this, SettingsActivity::class.java))
      return true
    }
    return super.onOptionsItemSelected(item)

  }
}
