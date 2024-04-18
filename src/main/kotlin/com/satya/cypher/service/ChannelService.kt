package com.satya.cypher.service

import com.satya.cypher.model.Channel
import com.satya.cypher.repository.ChannelRepository
import org.springframework.stereotype.Service

@Service
class ChannelService(private val channelRepository: ChannelRepository) {

    fun findAllChannels(): List<Channel> =
        channelRepository.findAll()

    fun createChannel(channel: Channel): Channel =
        channelRepository.save(channel)

    fun findChannelById(channelId: String): Channel? =
        channelRepository.findById(channelId).orElse(null)

    fun updateChannel(channelId: String, channel: Channel): Channel? {
        return if (channelRepository.existsById(channelId)) {
            channelRepository.save(channel.copy(channelId = channelId))
        } else {
            null
        }
    }

    fun deleteChannel(channelId: String) {
        if (channelRepository.existsById(channelId)) {
            channelRepository.deleteById(channelId)
        }
    }
}