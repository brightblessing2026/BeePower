package com.bright.beepower.models

data class Issue(

    var title: String = "",

    var description: String = "",

    var location: String = "",

    var id: String = "",

    var userId: String = ""   // ✅ ADDED (needed for admin filtering)

)