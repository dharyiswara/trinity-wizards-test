package com.trinitywizards.test.daryhilmyiswara.model

import com.google.gson.annotations.SerializedName

data class UserContact(
    @SerializedName("id") val id: String,
    @SerializedName("firstName") val firstname: String,
    @SerializedName("lastName") val lastname: String,
    @SerializedName("email") val email: String,
    @SerializedName("dob") val dob: String
)
