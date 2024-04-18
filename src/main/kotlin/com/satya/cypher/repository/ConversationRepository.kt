package com.satya.cypher.repository

import com.satya.cypher.model.Conversation
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ConversationRepository : JpaRepository<Conversation, String> {
    fun findByChannelId(channelId: String): List<Conversation>
    fun findByChannelIdAndConversationId(conversationId: String, channelId: String): Conversation?
    fun deleteByConversationIdAndChannelId(conversationId: String, channelId: String)
}