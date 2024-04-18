package com.satya.cypher.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
data class Conversation(
    @Id
    val conversationId: String,
    var channelId: String,
    var title: String,
    var description: String
)