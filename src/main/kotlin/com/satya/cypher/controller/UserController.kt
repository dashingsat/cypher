package com.satya.cypher.controller

import com.satya.cypher.model.CypherUser
import com.satya.cypher.service.UserService
import org.springframework.http.HttpStatus
import org.springframework.http.ResponseEntity
import org.springframework.web.bind.annotation.*

@RestController
@RequestMapping("/api/users")
class UserController(private val userService: UserService) {

    @GetMapping
    fun listUsers(): ResponseEntity<List<CypherUser>> =
        ResponseEntity.ok(userService.findAllUsers())

    @PostMapping
    fun createUser(@RequestBody cypherUser: CypherUser): ResponseEntity<CypherUser> =
        ResponseEntity(userService.createUser(cypherUser), HttpStatus.CREATED)

    @GetMapping("/{userId}")
    fun getUser(@PathVariable userId: String): ResponseEntity<CypherUser> =
        userService.findUserById(userId)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @PutMapping("/{userId}")
    fun updateUser(@PathVariable userId: String, @RequestBody cypherUser: CypherUser): ResponseEntity<CypherUser> =
        userService.updateUser(userId, cypherUser)?.let {
            ResponseEntity.ok(it)
        } ?: ResponseEntity(HttpStatus.NOT_FOUND)

    @DeleteMapping("/{userId}")
    fun deleteUser(@PathVariable userId: String): ResponseEntity<Void> =
        userService.deleteUser(userId).let {
            ResponseEntity<Void>(HttpStatus.NO_CONTENT)
        }
}
