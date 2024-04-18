package com.satya.cypher.model

import jakarta.persistence.Entity
import jakarta.persistence.Id

@Entity
class Message(
    @Id
    val messageId: String,
    var channelId: String,
    var conversationId: String,
    var sentByUserId: String,
    var content: String,
    var status: String  // e.g., 'sent', 'received', 'read'
)