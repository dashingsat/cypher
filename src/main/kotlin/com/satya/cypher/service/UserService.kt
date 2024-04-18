package com.satya.cypher.service

import com.satya.cypher.model.CypherUser
import com.satya.cypher.repository.UserRepository
import org.springframework.stereotype.Service

@Service
class UserService(private val userRepository: UserRepository) {

    fun findAllUsers(): List<CypherUser> =
        userRepository.findAll()

    fun createUser(cypherUser: CypherUser): CypherUser =
        userRepository.save(cypherUser)

    fun findUserById(userId: String): CypherUser? =
        userRepository.findById(userId).orElse(null)

    fun updateUser(userId: String, updatedCypherUser: CypherUser): CypherUser? {
        return userRepository.findById(userId).map { existingUser ->
            existingUser.email = updatedCypherUser.email
            existingUser.displayName = updatedCypherUser.displayName
            userRepository.save(existingUser)
        }.orElse(null)
    }

    fun deleteUser(userId: String) {
        userRepository.deleteById(userId)
    }
}