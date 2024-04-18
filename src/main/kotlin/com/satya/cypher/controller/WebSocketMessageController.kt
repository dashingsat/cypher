package com.satya.cypher.controller

import com.satya.cypher.model.Message
import com.satya.cypher.service.MessageService
import org.springframework.messaging.handler.annotation.MessageMapping
import org.springframework.messaging.handler.annotation.SendTo
import org.springframework.stereotype.Controller

@Controller
/*
This controller is only for incoming websockets, not being used now.

 */
class WebSocketMessageController(private val messageService: MessageService) {

    @MessageMapping("/message/send")
    @SendTo("/topic/messages")
    fun sendMessage(channelId:String,message: Message): Message {
        // Saving the message using the MessageService
        val savedMessage = messageService.createMessage(channelId, message.conversationId, message)
        // The message is then sent to all subscribers of "/topic/messages"
        return savedMessage
    }
}