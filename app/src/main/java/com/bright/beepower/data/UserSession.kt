package com.bright.beepower.data

object UserSession {

    var uid: String? = null
    var username: String? = null
    var email: String? = null
    var role: String? = null
    var meterNumber: String? = null
    var balance: Int = 0

    fun clear() {
        uid = null
        username = null
        email = null
        role = null
        meterNumber = null
        balance = 0
    }
}

