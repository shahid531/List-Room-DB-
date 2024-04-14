package com.mr.myroom.ui.fragment

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.navigation.fragment.findNavController
import com.mr.myroom.R
import com.mr.myroom.databinding.LayoutListFragmentBinding
import com.mr.myroom.listener.Listener
import com.mr.myroom.model.UsersItem
import com.mr.myroom.room.RoomDAO
import com.mr.myroom.ui.adapter.RoomAdapter
import com.mr.myroom.viewmodel.RoomViewModel
import dagger.hilt.android.AndroidEntryPoint
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@AndroidEntryPoint
class UserListFragment : Fragment(), Listener {
    @Inject
    lateinit var roomDAO: RoomDAO
    private var _binding: LayoutListFragmentBinding? = null
    private lateinit var roomAdapter: RoomAdapter
    private val binding get() = _binding!!
    private val viewmodel by viewModels<RoomViewModel>()
    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {

        _binding = LayoutListFragmentBinding.inflate(layoutInflater, container, false)
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        init()
    }

    private fun init() {
        viewmodel.getData()
        observe()
    }

    private fun observe() {
        viewmodel.roomList.observe(viewLifecycleOwner) {
            setAdapter(it as List<UsersItem>)
        }
    }


    private fun setAdapter(list: List<UsersItem>) {
        roomAdapter = RoomAdapter(requireContext(), this)
        roomAdapter.updateList(list)
        binding.listRv.adapter = roomAdapter
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }

    override fun onClick(usersItem: UsersItem, position: Int, index: Int) {
        if (position == 0) {
            val bundle = Bundle().apply {
                putParcelable("UsersItem", usersItem)
            }

            findNavController().navigate(R.id.action_userListFragment_to_userDetailFragment, bundle)
        } else if (position == 1) {
            CoroutineScope(Dispatchers.IO).launch {
                roomDAO.delete(index.toLong())
                viewmodel.getData()
            }
        }
    }
}