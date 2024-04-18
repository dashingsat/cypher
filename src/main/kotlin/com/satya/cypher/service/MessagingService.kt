package com.satya.cypher.service

import com.corundumstudio.socketio.SocketIOServer
import org.springframework.stereotype.Service
import java.util.*

@Service
class MessagingService(private val server: SocketIOServer) {

    fun sendMessageToAllClients(message: Any) {
        server.broadcastOperations.sendEvent("message_ev", message)
        println("Message sent to all clients: $message")
    }

    fun sendMessageToClient(sessionId: UUID, message: String) {
        server.getClient(sessionId)?.let { client ->
            client.sendEvent("message_event", message)
            println("Message sent to client $sessionId: $message")
        }
    }
}