package com.trinitywizards.test.daryhilmyiswara.ui.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.activity.viewModels
import androidx.recyclerview.widget.GridLayoutManager
import com.trinitywizards.test.daryhilmyiswara.adapter.ContactAdapter
import com.trinitywizards.test.daryhilmyiswara.databinding.ActivityMainBinding
import com.trinitywizards.test.daryhilmyiswara.helper.SpaceItemDecoration
import com.trinitywizards.test.daryhilmyiswara.model.UserContact
import com.trinitywizards.test.daryhilmyiswara.ui.editcontact.EditContactActivity
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {

    companion object {
        private const val EDIT_CONTACT_REQUEST_CODE = 101
    }

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
                setOnClickListener { userContact, position ->
                    EditContactActivity.startActivityForResult(
                        this@MainActivity, userContact, position,
                        EDIT_CONTACT_REQUEST_CODE
                    )
                }
            }
            addItemDecoration(SpaceItemDecoration(16))
        }

        binding.swContact.setOnRefreshListener {
            viewModel.getContactList(this)
        }
    }

    private fun subscribeToLiveData() {
        viewModel.getContactListLiveData().observe(this) {
            binding.swContact.isRefreshing = false
            contactAdapter.updateListContact(it)
        }
    }

    override fun onActivityResult(requestCode: Int, resultCode: Int, data: Intent?) {
        if (requestCode == EDIT_CONTACT_REQUEST_CODE && resultCode == RESULT_OK) {
            data?.extras?.let {
                val userContact =
                    it.getSerializable(EditContactActivity.USER_CONTACT) as UserContact
                val position = it.getInt(EditContactActivity.POSITION)
                contactAdapter.updateContact(userContact, position)
            }
        }
        super.onActivityResult(requestCode, resultCode, data)
    }
}