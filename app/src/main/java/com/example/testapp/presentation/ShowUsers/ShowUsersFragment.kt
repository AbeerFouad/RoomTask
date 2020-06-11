package com.example.testapp.presentation.ShowUsers

import android.os.Bundle
import android.view.View
import androidx.fragment.app.Fragment
import androidx.lifecycle.Observer
import com.example.testapp.R
import com.example.testapp.presentation.UserViewModel
import kotlinx.android.synthetic.main.show_users_fragment.*
import org.koin.android.ext.android.inject

class ShowUsersFragment : Fragment(R.layout.show_users_fragment) {

    private val viewModel: UserViewModel by inject()
    private val usersAdapter = UsersAdapter()

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel.getUsers()
        usersRv.adapter = usersAdapter
        viewModel.usersLiveData.observe(viewLifecycleOwner, Observer { state ->
            renderState(state)
        })
    }

    private fun renderState(state: UsersState) {
        when (state) {
            UsersState.Empty -> {
                emptyTv.visibility = View.VISIBLE
                usersRv.visibility = View.GONE
                progressBar.visibility = View.GONE
            }
            is UsersState.Success -> {
                emptyTv.visibility = View.GONE
                usersRv.visibility = View.VISIBLE
                progressBar.visibility = View.GONE
                usersAdapter.submitList(state.users)
            }
            UsersState.Loading -> {
                emptyTv.visibility = View.GONE
                usersRv.visibility = View.GONE
                progressBar.visibility = View.VISIBLE
            }
        }
    }
}