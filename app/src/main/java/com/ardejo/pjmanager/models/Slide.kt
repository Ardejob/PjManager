package com.ardejo.pjmanager.models

import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Slide(
        val titleRes: Int? = null,
        val bodyRes: Int? = null,
        val imageDrawable: Int? = null
) : Parcelable