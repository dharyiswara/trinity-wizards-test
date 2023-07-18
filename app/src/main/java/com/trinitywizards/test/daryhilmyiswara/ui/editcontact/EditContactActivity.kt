package com.trinitywizards.test.daryhilmyiswara.ui.editcontact

import android.app.Activity
import android.app.DatePickerDialog
import android.content.Intent
import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import androidx.core.content.ContextCompat
import androidx.core.os.bundleOf
import com.trinitywizards.test.daryhilmyiswara.R
import com.trinitywizards.test.daryhilmyiswara.databinding.ActivityEditContactBinding
import com.trinitywizards.test.daryhilmyiswara.model.UserContact
import java.text.SimpleDateFormat
import java.util.*

class EditContactActivity : AppCompatActivity() {

    companion object {
        @JvmStatic
        fun startActivityForResult(
            activity: Activity,
            userContact: UserContact,
            position: Int,
            requestCode: Int
        ) {
            activity.startActivityForResult(
                Intent(
                    activity,
                    EditContactActivity::class.java
                ).apply {
                    val bundle = bundleOf().apply {
                        putSerializable(USER_CONTACT, userContact)
                        putInt(POSITION, position)
                    }
                    putExtras(bundle)
                }, requestCode
            )
        }

        const val USER_CONTACT = "USER_CONTACT"
        const val POSITION = "POSITION"
    }

    private lateinit var binding: ActivityEditContactBinding

    private val datePickerDialog by lazy { DatePickerDialog(this) }
    private var userContact: UserContact? = null

    private val datePickerFormat by lazy { SimpleDateFormat("dd MMMM yyyy") }
    private val dateFormat by lazy { SimpleDateFormat("d/M/yyyy") }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)

        binding = ActivityEditContactBinding.inflate(layoutInflater)
        setContentView(binding.root)

        setupView()
    }

    private fun setupView() {
        binding.run {
            datePickerDialog.setOnDateSetListener { _, y, m, d ->
                val calendar = Calendar.getInstance()
                calendar.set(Calendar.YEAR, y)
                calendar.set(Calendar.MONTH, m)
                calendar.set(Calendar.DAY_OF_MONTH, d)
                etDob.setText(datePickerFormat.format(calendar.time))
            }
            intent.extras?.let {
                userContact = it.getSerializable(USER_CONTACT) as UserContact
                etFirstName.setText(userContact?.firstname)
                etLastName.setText(userContact?.lastname)
                etEmail.setText(userContact?.email)
                val dob = dateFormat.parse(userContact?.dob.orEmpty())
                    ?.let { date -> datePickerFormat.format(date) }
                etDob.setText(dob)
            }
            etDob.setOnClickListener {
                datePickerDialog.show()
                datePickerDialog.getButton(DatePickerDialog.BUTTON_NEGATIVE)?.setTextColor(
                    ContextCompat.getColor(
                        this@EditContactActivity,
                        R.color.black
                    )
                )
                datePickerDialog.getButton(DatePickerDialog.BUTTON_POSITIVE)?.setTextColor(
                    ContextCompat.getColor(
                        this@EditContactActivity,
                        R.color.black
                    )
                )
            }
            tvCancel.setOnClickListener {
                setResult(RESULT_CANCELED)
                finish()
            }
            tvSave.setOnClickListener {
                if (checkField()) {
                    val newIntent = Intent().apply {
                        val bundle = Bundle().apply {
                            putSerializable(
                                USER_CONTACT,
                                UserContact(
                                    userContact?.id.orEmpty(),
                                    etFirstName.text.toString(),
                                    etLastName.text.toString(),
                                    etEmail.text.toString(),
                                    getDob().orEmpty()
                                )
                            )
                            putInt(POSITION, intent?.getIntExtra(POSITION, 0) ?: 0)
                        }
                        putExtras(bundle)
                    }
                    setResult(RESULT_OK, newIntent)
                    finish()
                }
            }
        }
    }

    private fun checkField(): Boolean {
        binding.run {
            if (etFirstName.text.toString().isBlank()) {
                etFirstName.error = "Required"
            }
            if (etLastName.text.toString().isBlank()) {
                etLastName.error = "Required"
            }
            return etFirstName.text.toString().isNotBlank()
                    && etLastName.text.toString().isNotBlank()
        }
    }

    private fun getDob(): String? {

        return datePickerFormat.parse(binding.etDob.text.toString())?.let { dateFormat.format(it) }
    }

}