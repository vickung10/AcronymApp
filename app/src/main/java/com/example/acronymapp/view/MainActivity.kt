package com.example.acronymapp.view

import android.os.Bundle
import androidx.activity.viewModels
import androidx.appcompat.app.AppCompatActivity
import androidx.core.widget.doAfterTextChanged
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.acronymapp.ClickHandler
import com.example.acronymapp.R
import com.example.acronymapp.adapter.MeaningsAdapter
import com.example.acronymapp.databinding.ActivityMainBinding
import com.example.acronymapp.viewmodel.MainViewModel

class MainActivity : AppCompatActivity() {

    private val viewModel by viewModels<MainViewModel>()
    private lateinit var binding: ActivityMainBinding

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = DataBindingUtil.setContentView(this, R.layout.activity_main)
        setupBindings()
        setupObservers()
    }

    private fun setupBindings() {
        binding.apply {
            clickHandler = object : ClickHandler {
                override fun clearSearchText() {
                    binding.etSearch.setText("")
                }
            }
            vm = viewModel
            rvMeanings.apply {
                layoutManager = LinearLayoutManager(context)
                setHasFixedSize(true)
                adapter = MeaningsAdapter()
            }
            etSearch.doAfterTextChanged {
                viewModel.showClearTextIcon.set(it?.count() ?: 0 > 0)
                it?.toString()?.let { acronym ->
                    if (acronym.isNotEmpty()) viewModel.fetchMeaning(acronym)
                }
            }
        }
    }

    private fun setupObservers() {
        viewModel.acronymResponse.observe(this, Observer {
            (binding.rvMeanings.adapter as MeaningsAdapter).loadMeanings(it.lfs)
        })
    }
}
