package com.latihan_android.expert_submission.detail

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Toast
import com.bumptech.glide.Glide
import com.latihan_android.expert_submission.R
import com.latihan_android.core.data.Resource
import com.latihan_android.core.domain.module.DetailUser
import com.latihan_android.core.domain.module.Favorite
import com.latihan_android.expert_submission.databinding.ActivityDetailBinding
import com.latihan_android.expert_submission.ui.UserAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel

class DetailActivity : AppCompatActivity() {
    private val detailViewModel:DetailViewModel by viewModel()
    private var _binding:ActivityDetailBinding?=null
    private val binding get() = _binding!!

    companion object{
        const val EXTRA_DATA = "extra_data"
        const val EXTRA_FAVORITE = "extra_favorite"
    }


    override fun onCreate(savedInstanceState: Bundle?) {


        super.onCreate(savedInstanceState)
        _binding = ActivityDetailBinding.inflate(layoutInflater)
        setContentView(binding.root)

       val detailUsername=intent.getStringExtra(EXTRA_DATA)
       val userFavorite=intent.getBooleanExtra(EXTRA_FAVORITE,false)

        detailViewModel.getDetail(detailUsername!!).observe(this){detail ->
            if (detail.data !=null){
                when (detail){
                    is Resource.Error ->{
                        binding.progressBar.visibility= View.GONE
                        Toast.makeText(this,detail.message,Toast.LENGTH_SHORT).show()
                    }
                    is Resource.Loading ->{
                        binding.progressBar.visibility = View.VISIBLE
                    }
                    is Resource.Success -> {
                        binding.progressBar.visibility=View.GONE
                        showDetailUser(detail.data,userFavorite)
                    }
                }
            }

        }
    }

    private fun showDetailUser(user: DetailUser?, isFavorite: Boolean){
        detailViewModel.let{
            supportActionBar?.title = user?.username
            binding.userName.text=user?.username
            binding.realName.text=user?.name
            binding.UserBio.text=user?.bio
            binding.TwitterUser.text=user?.twitterUsername
            binding.FollowedCount.text=user?.following.toString()
            binding.FollowersCount.text=user?.followers.toString()
            Glide.with(this@DetailActivity)
                .load(user?.avatarUrl)
                .into(binding.avatarImage)
            setFavorite(Favorite(userName = user!!.username, userID = user.userID, isFavorite =isFavorite , userAvatar = user.avatarUrl))
        }

    }

    private fun setFavorite(favorite: Favorite){
        var statusFavorite = favorite.isFavorite
        setStatusFavorite(statusFavorite)
        binding.FavoriteButton.setOnClickListener {
            statusFavorite = !statusFavorite
            detailViewModel.setFavoriteUser(favorite,statusFavorite)
            setStatusFavorite(statusFavorite)
        }
    }


    private fun setStatusFavorite(statusFavorite: Boolean){
        if(statusFavorite){
            binding.FavoriteButton.text= getString(R.string.batalkan_favorit)
        }else{
            binding.FavoriteButton.text=getString(R.string.jadikan_favorit)
        }
    }

    override fun onDestroy() {
        super.onDestroy()
        _binding=null

    }
}