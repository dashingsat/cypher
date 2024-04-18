package com.satya.cypher.controller

import com.satya.cypher.model.Channel
import com.satya.cypher.service.ChannelService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/channels")
class ChannelController(private val channelService: ChannelService) {

    @GetMapping
    fun listChannels(): ResponseEntity<List<Channel>> =
        ResponseEntity.ok(channelService.findAllChannels())

    @PostMapping
    fun createChannel(@RequestBody channel: Channel): ResponseEntity<Channel> =
        ResponseEntity(channelService.createChannel(channel), HttpStatus.CREATED)

    @GetMapping("/{channelId}")
    fun getChannel(@PathVariable channelId: String): ResponseEntity<Channel> =
        channelService.findChannelById(channelId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @PutMapping("/{channelId}")
    fun updateChannel(@PathVariable channelId: String, @RequestBody channel: Channel): ResponseEntity<Channel> =
        channelService.updateChannel(channelId, channel)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @DeleteMapping("/{channelId}")
    fun deleteChannel(@PathVariable channelId: String): ResponseEntity<Void> =
        channelService.deleteChannel(channelId).let {
            ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
}
