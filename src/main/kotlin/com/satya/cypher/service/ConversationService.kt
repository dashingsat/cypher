package com.satya.cypher.service

import com.satya.cypher.model.Conversation
import com.satya.cypher.repository.ConversationRepository
import org.springframework.stereotype.Service

@Service
class ConversationService(private val conversationRepository: ConversationRepository) {

    fun findAllConversations(channelId: String): List<Conversation> =
        conversationRepository.findByChannelId(channelId)

    fun createConversation(channelId: String, conversation: Conversation): Conversation {
        conversation.channelId = channelId
        return conversationRepository.save(conversation)
    }

    fun findConversationById(channelId: String, conversationId: String): Conversation? =
        conversationRepository.findByChannelIdAndConversationId(conversationId, channelId)

    fun updateConversation(channelId: String, conversationId: String, updatedConversation: Conversation): Conversation? {
        return conversationRepository.findByChannelIdAndConversationId(conversationId, channelId)?.let { existingConversation ->
            existingConversation.title = updatedConversation.title
            existingConversation.description = updatedConversation.description
            conversationRepository.save(existingConversation)
        }
    }

    fun deleteConversation(channelId: String, conversationId: String) {
        conversationRepository.deleteByConversationIdAndChannelId(conversationId, channelId)
    }
}