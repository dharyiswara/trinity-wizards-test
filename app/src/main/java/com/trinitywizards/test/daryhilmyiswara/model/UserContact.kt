package com.trinitywizards.test.daryhilmyiswara.model

import com.google.gson.annotations.SerializedName
import java.io.Serializable

data class UserContact(
    @SerializedName("id") val id: String,
    @SerializedName("firstName") var firstname: String,
    @SerializedName("lastName") var lastname: String,
    @SerializedName("email") var email: String,
    @SerializedName("dob") var dob: String
) : Serializable
