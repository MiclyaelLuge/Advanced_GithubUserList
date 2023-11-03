package com.latihan_android.expert_submission.home

import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.recyclerview.widget.LinearLayoutManager
import com.latihan_android.expert_submission.R
import com.latihan_android.core.data.Resource
import com.latihan_android.expert_submission.databinding.FragmentHomeBinding
import com.latihan_android.expert_submission.detail.DetailActivity
import com.latihan_android.expert_submission.ui.UserAdapter
import org.koin.androidx.viewmodel.ext.android.viewModel


class HomeFragment : Fragment() {

    private val homeViewModel:HomeViewModel by viewModel()
    private var _binding : FragmentHomeBinding? = null
    private val binding get() = _binding!!

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding =FragmentHomeBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        if (activity != null) {
            val userAdapter = UserAdapter()
            userAdapter.onItemClick = { data ->
                val intent = Intent(activity, DetailActivity::class.java)
                Log.e("Testa",data.userName)
                intent.putExtra(DetailActivity.EXTRA_DATA, data.userName)
                intent.putExtra(DetailActivity.EXTRA_FAVORITE,data.isFavorite)
                startActivity(intent)
            }


            homeViewModel.user.observe(viewLifecycleOwner) { favorite ->
                if (favorite != null) {
                    when (favorite) {
                        is Resource.Error -> {
                            binding.progressBar.visibility = View.GONE
                            Toast.makeText(activity,favorite.message,Toast.LENGTH_SHORT).show()
                        }
                        is Resource.Loading -> {
                            binding.progressBar.visibility = View.VISIBLE
                        }
                        is Resource.Success -> {
                            binding.progressBar.visibility = View.GONE
                            userAdapter.setData(favorite.data)
                        }
                    }
                }
            }

            with(binding.userList){
                layoutManager=LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter=userAdapter
            }
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }


}