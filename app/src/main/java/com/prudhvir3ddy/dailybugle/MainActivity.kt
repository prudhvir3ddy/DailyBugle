package com.prudhvir3ddy.dailybugle

import android.content.Intent
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.appcompat.app.AppCompatActivity
import com.prudhvir3ddy.dailybugle.ui.SettingsActivity

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

//        val viewModelJob = Job()
//
//        val coroutineScope = CoroutineScope(viewModelJob + Dispatchers.Main)
//
//        coroutineScope.launch {
//            val getNewsDeferred = NewsApi.newsService.getEveryThing(BuildConfig.apiNews)
//            try {
//
//                val resultList = getNewsDeferred.await()
//                Log.d("newsResult", "${resultList.totalResults}")
//
//            } catch (e: Exception) {
//                Log.d("newsResult", e.message!!)
//            }
//        }

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {

        if (item.itemId == R.id.menu_item_settings) {
            startActivity(Intent(this, SettingsActivity::class.java))
            return true
        }
        return super.onOptionsItemSelected(item)


    }
}
