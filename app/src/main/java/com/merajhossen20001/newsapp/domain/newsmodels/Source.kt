package com.merajhossen20001.newsapp.domain.newsmodels

import android.os.Parcelable
import kotlinx.parcelize.Parcelize


@Parcelize
data class Source(
    val id: String,
    val name: String
): Parcelable