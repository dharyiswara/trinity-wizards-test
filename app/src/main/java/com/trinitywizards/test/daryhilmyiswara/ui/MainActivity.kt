package com.trinitywizards.test.daryhilmyiswara.ui

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.trinitywizards.test.daryhilmyiswara.R
import com.trinitywizards.test.daryhilmyiswara.adapter.ContactAdapter
import com.trinitywizards.test.daryhilmyiswara.databinding.ActivityMainBinding
import com.trinitywizards.test.daryhilmyiswara.helper.SpaceItemDecoration
import com.trinitywizards.test.daryhilmyiswara.ui.main.MainViewModel
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding

    private val viewModel by viewModels<MainViewModel>()
    private val contactAdapter by lazy { ContactAdapter() }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
        subscribeToLiveData()
        viewModel.getContactList(this)
    }

    private fun setupView() {
        binding.rvContact.run {
            layoutManager = GridLayoutManager(this@MainActivity, 2)
            adapter = contactAdapter.apply {
                setOnClickListener { }
            }
            addItemDecoration(SpaceItemDecoration(16))
        }
    }

    private fun subscribeToLiveData() {
        viewModel.getContactListLiveData().observe(this) {
            contactAdapter.updateListGames(it)
        }
    }
}