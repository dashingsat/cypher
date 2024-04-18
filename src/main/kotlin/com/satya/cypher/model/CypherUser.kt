package com.satya.cypher.model

import jakarta.persistence.Entity
import jakarta.persistence.Id


@Entity
class CypherUser(
    @Id
    val userId: String,
    var displayName: String,
    var email: String
)
