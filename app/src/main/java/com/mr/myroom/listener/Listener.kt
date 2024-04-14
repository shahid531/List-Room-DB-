package com.mr.myroom.listener

import com.mr.myroom.model.UsersItem

interface Listener {
    fun onClick(usersItem: UsersItem, position: Int, index: Int)
}