package com.trinitywizards.test.daryhilmyiswara.ui.main

import android.content.Context
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.trinitywizards.test.daryhilmyiswara.model.UserContact
import com.trinitywizards.test.daryhilmyiswara.repository.MainRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MainViewModel @Inject constructor(
    private val mainRepository: MainRepository
) : ViewModel() {

    private val contactListLiveData = MutableLiveData<List<UserContact>>()

    fun getContactListLiveData(): LiveData<List<UserContact>> = contactListLiveData

    fun getContactList(context: Context) {
        contactListLiveData.value = mainRepository.getContactList(context)
    }
}