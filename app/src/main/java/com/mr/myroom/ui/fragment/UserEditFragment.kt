package com.mr.myroom.ui.fragment

import android.os.Bundle
import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import coil.load
import com.mr.myroom.databinding.LayoutCardBinding
import com.mr.myroom.model.UsersItem
import com.mr.myroom.room.RoomDAO
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserEditFragment : Fragment() {
    private var _binding: LayoutCardBinding? = null
    private val binding get() = _binding!!

    @Inject
    lateinit var roomDAO: RoomDAO

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
            binding.imgSave.apply {
                visibility = View.VISIBLE
                setOnClickListener {
                    CoroutineScope(Dispatchers.IO).launch {
                        val name = binding.txtFirstName.text.toString()
                        val email = binding.txtEmail.text.toString()
                        Log.d("FULL_NAME", name)
                        roomDAO.update(
                            firstName = name,
                            email = email,
                            id = userItem?.id?.toLong() ?: 0
                        )
                    }
                }
            }
            txtFirstName.setText(userItem?.firstName)
            txtEmail.setText(userItem?.email)
            profileImg.load(userItem?.image)
            imgEdit.visibility = View.VISIBLE
            txtFirstName.isEnabled = true
            txtEmail.isEnabled = true
        }
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}