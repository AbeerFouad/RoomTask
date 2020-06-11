package com.example.testapp.presentation.AddUser

import android.os.Bundle
import android.view.View
import android.widget.Toast
import android.widget.Toast.LENGTH_SHORT
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import androidx.navigation.fragment.findNavController
import com.example.testapp.R
import com.example.testapp.presentation.UserViewModel
import kotlinx.android.synthetic.main.add_user_fragment.*
import org.koin.android.ext.android.inject


class AddUserFragment : Fragment(R.layout.add_user_fragment) {
    private val viewModel: UserViewModel by inject()
    private lateinit var selectedGender: String

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        selectedGender = getString(R.string.male)
        setListeners()

        viewModel.addedUserLiveData.observe(viewLifecycleOwner, Observer {
            if (it != null) {
                Toast.makeText(
                    requireContext(),
                    getString(R.string.user_saved_successfully),
                    LENGTH_SHORT
                ).show()
                reset()
            }

        })
    }

    private fun reset() {
        nameEt.setText("")
        nameEt.clearFocus()
        nameEt.error = null
        ageEt.setText("")
        ageEt.clearFocus()
        jobTitleEt.setText("")
        jobTitleEt.clearFocus()
        genderRadGrp.check(R.id.maleRadBtn)
    }

    private fun setListeners() {
        saveUserBtn.setOnClickListener {
            if (validate()) {
                viewModel.addUser(
                    name = nameEt.text.toString(),
                    age = if (ageEt.text.toString().isNotEmpty()) ageEt.text.toString()
                        .toInt() else null,
                    job = if (jobTitleEt.text.toString()
                            .isNotEmpty()
                    ) jobTitleEt.text.toString() else null,
                    gender = selectedGender
                )
            }
        }

        showUsersBtn.setOnClickListener {
            reset()
            findNavController().navigate(R.id.action_addUserFragment_to_showUsersFragment)
        }

        genderRadGrp.setOnCheckedChangeListener { group, checkedId ->
            when (checkedId) {
                R.id.maleRadBtn -> selectedGender = getString(R.string.male)
                R.id.femaleRadBtn -> selectedGender = getString(R.string.female)
            }
        }
    }

    private fun validate(): Boolean {
        return if (nameEt.text.toString().isEmpty()) {
            nameEt.error = getString(R.string.name_required)
            false
        } else {
            true
        }
    }

    override fun onDestroyView() {
        viewModel.reset()
        super.onDestroyView()

    }
}