package com.satya.cypher.controller

import com.satya.cypher.model.Message
import com.satya.cypher.service.MessageService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/channels/{channelId}/conversations/{conversationId}/messages")
class MessageController(private val messageService: MessageService) {

    @GetMapping
    fun listMessages(@PathVariable channelId: String, @PathVariable conversationId: String): ResponseEntity<List<Message>> =
        ResponseEntity.ok(messageService.findAllMessages(channelId, conversationId))

    @PostMapping
    fun createMessage(
        @PathVariable channelId: String,
        @PathVariable conversationId: String,
        @RequestBody message: Message
    ): ResponseEntity<Message> =
        ResponseEntity(messageService.createMessage(channelId, conversationId, message), HttpStatus.CREATED)

    @GetMapping("/{messageId}")
    fun getMessage(
        @PathVariable channelId: String,
        @PathVariable conversationId: String,
        @PathVariable messageId: String
    ): ResponseEntity<Message> =
        messageService.findMessageById(channelId, conversationId, messageId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @PutMapping("/{messageId}")
    fun updateMessage(
        @PathVariable channelId: String,
        @PathVariable conversationId: String,
        @PathVariable messageId: String,
        @RequestBody message: Message
    ): ResponseEntity<Message> =
        messageService.updateMessage(channelId, conversationId, messageId, message)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @DeleteMapping("/{messageId}")
    fun deleteMessage(
        @PathVariable channelId: String,
        @PathVariable conversationId: String,
        @PathVariable messageId: String
    ): ResponseEntity<Void> =
        messageService.deleteMessage(channelId, conversationId, messageId).let {
            ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
}