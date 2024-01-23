package com.example.amphibian2.network

import kotlinx.serialization.Serializable

@Serializable
data class AmphibiansItem(
    val description: String,
    val img_src: String,
    val name: String,
    val type: String
)