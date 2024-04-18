package com.satya.cypher.repository

import com.satya.cypher.model.Channel
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface ChannelRepository : JpaRepository<Channel, String>