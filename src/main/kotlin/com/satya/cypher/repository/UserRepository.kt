package com.satya.cypher.repository

import com.satya.cypher.model.CypherUser
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface UserRepository : JpaRepository<CypherUser, String>
