package com.example.endlessscrollsampleproject.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.endlessscrollsampleproject.R
import com.example.endlessscrollsampleproject.databinding.ActivityMainBinding
import com.example.endlessscrollsampleproject.model.User
import com.example.endlessscrollsampleproject.ui.adapter.MainAdapter
import com.example.endlessscrollsampleproject.viewmodel.MainViewModel
import dagger.hilt.android.AndroidEntryPoint
import javax.inject.Inject

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private val viewModel: MainViewModel by viewModels()
    @Inject lateinit var adapter: MainAdapter
    private lateinit var binding: ActivityMainBinding

    private var currentUserList = mutableListOf<User?>()
    private var isLoading = false
    private var currentPageIndex = 1

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        binding.adapter = adapter

        setListener()
        setRecyclerView()

        viewModel.getSearchUserResult("tom", currentPageIndex)
    }

    private fun setListener() {
        viewModel.userList.observe(this) {
            currentUserList.clear()
            currentUserList.addAll(it)
            adapter.submitList(currentUserList)
            isLoading = false
        }
    }

    private fun setRecyclerView() {
        binding.rvUserList.addOnScrollListener(object: RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                val layoutManager = binding.rvUserList.layoutManager as LinearLayoutManager

                if (!isLoading) {
                    if (layoutManager.findLastCompletelyVisibleItemPosition() ==  currentUserList.size - 1) {
                        loadNextUsers()
                        isLoading = true
                    }
                }
            }
        })
    }

    private fun loadNextUsers() {
        currentUserList.add(null)
        adapter.submitList(currentUserList)
        viewModel.getSearchUserResult("tom", ++currentPageIndex)
    }
}