package com.satya.cypher.controller

import com.satya.cypher.model.Conversation
import com.satya.cypher.service.ConversationService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/channels/{channelId}/conversations")
class ConversationController(private val conversationService: ConversationService) {

    @GetMapping
    fun listConversations(@PathVariable channelId: String): ResponseEntity<List<Conversation>> =
        ResponseEntity.ok(conversationService.findAllConversations(channelId))

    @PostMapping
    fun createConversation(@PathVariable channelId: String, @RequestBody conversation: Conversation): ResponseEntity<Conversation> =
        ResponseEntity(conversationService.createConversation(channelId, conversation), HttpStatus.CREATED)

    @GetMapping("/{conversationId}")
    fun getConversation(@PathVariable channelId: String, @PathVariable conversationId: String): ResponseEntity<Conversation> =
        conversationService.findConversationById(channelId, conversationId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @PutMapping("/{conversationId}")
    fun updateConversation(@PathVariable channelId: String, @PathVariable conversationId: String, @RequestBody conversation: Conversation): ResponseEntity<Conversation> =
        conversationService.updateConversation(channelId, conversationId, conversation)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @DeleteMapping("/{conversationId}")
    fun deleteConversation(@PathVariable channelId: String, @PathVariable conversationId: String): ResponseEntity<Void> =
        conversationService.deleteConversation(channelId, conversationId).let {
            ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
}