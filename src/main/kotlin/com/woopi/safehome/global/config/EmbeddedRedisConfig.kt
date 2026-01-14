package com.woopi.safehome.global.config


import jakarta.annotation.PostConstruct
import jakarta.annotation.PreDestroy
import org.springframework.beans.factory.annotation.Value
import org.springframework.context.annotation.Configuration
import org.springframework.context.annotation.Profile
import redis.embedded.RedisServer
import java.io.IOException
import java.net.ServerSocket

@Profile("local", "test")
@Configuration
class EmbeddedRedisConfig(
    @Value("\${spring.data.redis.port:6379}")
    private val redisPort: Int
) {
    private var redisServer: RedisServer? = null

    @PostConstruct
    fun startRedis() {
        val availablePort = if (isPortInUse(redisPort)) {
            findAvailablePort()
        } else {
            redisPort
        }

        redisServer = RedisServer.builder()
            .port(availablePort)
            .setting("maxheap 128mb")  // 이 줄 추가
            .build()

        redisServer?.start()
        println("Embedded Redis started on port $availablePort")
    }

    @PreDestroy
    fun stopRedis() {
        redisServer?.stop()
        println("Embedded Redis stopped")
    }

    private fun isPortInUse(port: Int): Boolean {
        return try {
            ServerSocket(port).use { false }
        } catch (e: IOException) {
            true
        }
    }

    private fun findAvailablePort(): Int {
        return try {
            ServerSocket(0).use { socket ->
                socket.localPort
            }
        } catch (e: IOException) {
            throw IllegalStateException("사용 가능한 포트를 찾을 수 없습니다", e)
        }
    }
}