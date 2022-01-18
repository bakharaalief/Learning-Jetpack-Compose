package com.bakharaalief.learningcompose

import android.os.Parcelable
import kotlinx.parcelize.Parcelize

@Parcelize
data class Idol(val photo : Int, val title : String, val contentDesc : String) : Parcelable
