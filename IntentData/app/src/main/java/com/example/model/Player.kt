package com.example.model

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Player(
    var name: String,
    var age: Int,
    var team: String
) : Parcelable {
    override fun toString(): String {
        return "name: $name, age: $age, team: $team"
    }
}
