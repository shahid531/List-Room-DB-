package com.mr.myroom.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.navigation.fragment.findNavController
import coil.load
import com.mr.myroom.R
import com.mr.myroom.databinding.LayoutCardBinding
import com.mr.myroom.model.UsersItem
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class UserDetailFragment : Fragment() {

    private var _binding: LayoutCardBinding? = null
    private val binding get() = _binding!!
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = LayoutCardBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        val userItem = requireArguments().getParcelable<UsersItem>("UsersItem")

        binding.apply {
            txtFirstName.setText(userItem?.firstName)
            txtEmail.setText(userItem?.email)
            profileImg.load(userItem?.image)
            imgEdit.visibility = View.VISIBLE
        }

        binding.imgEdit.setOnClickListener {
            val bundle = Bundle().apply {
                putParcelable("UsersItem", userItem)
            }
            findNavController().navigate(
                R.id.action_userDetailFragment_to_userEditFragment, bundle
            )
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}