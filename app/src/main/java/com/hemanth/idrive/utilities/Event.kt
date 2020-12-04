package com.hemanth.idrive.utilities


class Event<T>(private val content: T) {

    private var pending = true

    fun getContentIfNotPending(): T? {
        return if (pending) {
            pending = false
            content
        } else null

    }

    fun getContent(): T = content
}