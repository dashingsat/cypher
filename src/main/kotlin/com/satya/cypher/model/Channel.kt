package com.satya.cypher.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Channel(
    @Id
    val channelId: String,
    val name: String,
    val type: String
)