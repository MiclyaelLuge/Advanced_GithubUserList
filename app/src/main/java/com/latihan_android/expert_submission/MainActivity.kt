package com.latihan_android.expert_submission

import android.content.Intent
import android.net.Uri
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.Menu
import android.view.MenuItem
import androidx.activity.OnBackPressedCallback
import com.latihan_android.expert_submission.databinding.ActivityMainBinding
import com.latihan_android.expert_submission.home.HomeFragment

class MainActivity : AppCompatActivity() {
    private lateinit var binding : ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        supportActionBar?.title= getString(R.string.github_search_user)
        if (savedInstanceState==null){
            supportFragmentManager.beginTransaction()
                .replace(R.id.nav_host_fragment,HomeFragment())
                .commit()
        }

        onBackPressedDispatcher.addCallback(this, object:OnBackPressedCallback(true){
            override fun handleOnBackPressed() {
                supportFragmentManager.popBackStack()
            }

        })

    }

    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater = menuInflater
        inflater.inflate(R.menu.activity_menu,menu)
        return true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId){
            R.id.home ->{
                supportFragmentManager.beginTransaction()
                    .replace(R.id.nav_host_fragment,HomeFragment())
                    .commit()
                true
            }

            R.id.favorite ->{
                val uri = Uri.parse("expert_submission://favorite")
                startActivity(Intent(Intent.ACTION_VIEW,uri))
                true
            }

            else -> true
        }
    }


}