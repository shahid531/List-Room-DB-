package com.mr.myroom.viewmodel

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.mr.myroom.model.UsersItem
import com.mr.myroom.repository.Repository
import com.mr.myroom.room.RoomDAO
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RoomViewModel @Inject constructor(
    private val repository: Repository,
    private val roomDAO: RoomDAO
) : ViewModel() {
    private val _roomList = MutableLiveData<List<UsersItem>>()
    val roomList get() = _roomList
    fun getData() {

        viewModelScope.launch {
            if (roomDAO.getData().isEmpty()) {
                repository.getData()
                    .collect {
                        roomDAO.insert(it.body()?.users as List<UsersItem>)
                        Log.d("DATA_BASE_LIST_1", "${roomDAO.getData()}")
                        _roomList.value = roomDAO.getData()
                    }
            }
            Log.d("DATA_BASE_LIST", "${roomDAO.getData()}")
            _roomList.value = roomDAO.getData()
        }
    }
}