package com.satya.cypher.repository

import com.satya.cypher.model.Message
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface MessageRepository : JpaRepository<Message, String> {
    fun findByConversationId(conversationId: String): List<Message>
    fun findByMessageIdAndConversationId(messageId: String, conversationId: String): Message?
    fun deleteByMessageIdAndConversationId(messageId: String, conversationId: String)
}