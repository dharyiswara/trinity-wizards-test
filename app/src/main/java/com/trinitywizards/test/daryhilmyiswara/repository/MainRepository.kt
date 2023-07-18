package com.trinitywizards.test.daryhilmyiswara.repository

import android.content.Context
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import com.trinitywizards.test.daryhilmyiswara.model.UserContact
import com.trinitywizards.test.daryhilmyiswara.helper.JsonHelper
import javax.inject.Inject

class MainRepository @Inject constructor(
    private val gson: Gson
) {

    fun getContactList(context: Context): List<UserContact> {
        val jsonString = JsonHelper.getJsonDataFromAsset(context, "data.json")

        return gson.fromJson(jsonString, object : TypeToken<List<UserContact>>() {}.type)
    }

}