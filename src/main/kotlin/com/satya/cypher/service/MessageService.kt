package com.satya.cypher.service

import com.satya.cypher.model.Channel
import com.satya.cypher.model.Conversation
import com.satya.cypher.model.Message
import com.satya.cypher.repository.ChannelRepository
import com.satya.cypher.repository.ConversationRepository
import com.satya.cypher.repository.MessageRepository
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Service

@Service
class MessageService(private val messageRepository: MessageRepository ,
                     private val messagingTemplate: SimpMessagingTemplate,
                     private val conversationRepository: ConversationRepository,
                     private val channelRepository: ChannelRepository
    ) {

    fun findAllMessages(channelId: String, conversationId: String): List<Message> =
        messageRepository.findByConversationId(conversationId)

    fun createMessage(channelId: String, conversationId: String, message: Message): Message {

        val channel = channelRepository.findById(channelId)
            .orElseGet {
                // Create and save a new channel if it does not exist
                channelRepository.save(Channel(channelId = channelId, name = channelId, type = "public"))
            }

        // Ensure the conversation exists or create it
        val conversation = conversationRepository.findById(conversationId)
            .orElseGet {
                // Create and save a new conversation if it does not exist
                conversationRepository.save(Conversation(conversationId = conversationId,
                    channelId = channelId,
                    title = "Generated Title",
                    description = "A beautiful conversation"
                    ))
            }
        message.conversationId = conversationId
        message.channelId = channelId

        messagingTemplate.convertAndSend("/topic/conversations/", message)
        return messageRepository.save(message)
    }

    fun findMessageById(channelId: String, conversationId: String, messageId: String): Message? =
        messageRepository.findByMessageIdAndConversationId(messageId, conversationId)

    fun updateMessage(channelId: String, conversationId: String, messageId: String, updatedMessage: Message): Message? {
        return messageRepository.findByMessageIdAndConversationId(messageId, conversationId)?.let { existingMessage ->
            existingMessage.content = updatedMessage.content
            existingMessage.status = updatedMessage.status
            messagingTemplate.convertAndSend("/topic/conversations/$channelId", existingMessage)
            messageRepository.save(existingMessage)

        }
    }

    fun deleteMessage(channelId: String, conversationId: String, messageId: String) {
        messageRepository.deleteByMessageIdAndConversationId(messageId, conversationId)
    }
}