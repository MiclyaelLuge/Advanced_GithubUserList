package com.latihan_android.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.View
import androidx.activity.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan_android.expert_submission.detail.DetailActivity
import com.latihan_android.expert_submission.ui.UserAdapter
import com.latihan_android.favorite.databinding.ActivityFavoriteBinding
import org.koin.androidx.viewmodel.ext.android.viewModel
import org.koin.core.context.loadKoinModules

class FavoriteActivity : AppCompatActivity() {
    private lateinit var binding:ActivityFavoriteBinding
    private val favoriteViewModel: FavoriteViewModel by viewModel()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityFavoriteBinding.inflate(layoutInflater)
        setContentView(binding.root)

        loadKoinModules(favoriteModule)

        supportActionBar?.title="Favorite"


        val userAdapter = UserAdapter()

        favoriteViewModel.favoriteUser.observe(this) { user ->
            Log.e("Test",user.toString())
            binding.progressBar.visibility = View.VISIBLE
            if (user.isEmpty()) {
                binding.favoriteList.visibility = View.GONE
                binding.progressBar.visibility = View.GONE
            } else {
                binding.progressBar.visibility = View.GONE
                userAdapter.setData(user)
            }
            with(binding.favoriteList) {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = userAdapter
            }
        }

        userAdapter.onItemClick = { data ->
            val intent = Intent(this@FavoriteActivity, DetailActivity::class.java)
            intent.putExtra(DetailActivity.EXTRA_DATA, data.userName)
            intent.putExtra(DetailActivity.EXTRA_FAVORITE, data.isFavorite)
            startActivity(intent)
        }
    }
}