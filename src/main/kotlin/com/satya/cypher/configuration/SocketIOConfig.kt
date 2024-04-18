package com.satya.cypher.configuration

import com.corundumstudio.socketio.Configuration
import com.corundumstudio.socketio.SocketIOServer
import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.context.annotation.Bean
import org.springframework.stereotype.Component

@Component
class SocketIoConfig {

    private val server: SocketIOServer by lazy {
        val config = Configuration()
        config.hostname = "localhost"
        config.port = 9092  // Set to an appropriate port
        SocketIOServer(config).apply {
            addConnectListener { client ->
                println("Client connected: ${client.sessionId}")
            }
            addDisconnectListener { client ->
                println("Client disconnected: ${client.sessionId}")
            }
            addEventListener("message_event", String::class.java) { client, data, _ ->
                println("Received message: $data")
            }
        }
    }

    @PostConstruct
    fun startServer() {
        server.start()
        println("Socket.IO server started")
    }

    @PreDestroy
    fun stopServer() {
        server.stop()
        println("Socket.IO server stopped")
    }

    @Bean
    fun socketIOServer(): SocketIOServer = server
}