package com.woopi.safehome.domain.adapter.outbound.websocket

import com.woopi.safehome.domain.analysis.application.port.outbound.AnalysisProgressPort
import com.woopi.safehome.global.enums.AnalysisStep
import com.woopi.safehome.global.enums.JobStatus
import org.springframework.messaging.simp.SimpMessagingTemplate
import org.springframework.stereotype.Component
import java.time.LocalDateTime

@Component
class AnalysisWebSocketNotifier(
    private val messagingTemplate: SimpMessagingTemplate
): AnalysisProgressPort {
    override fun notifyStep(
        jobId: String,
        status: JobStatus,
        step: AnalysisStep?,
        message: String
    ) {
        val payload = mapOf(
            "jobId" to jobId,
            "status" to status.name,
            "step" to step?.name,
            "message" to message,
            "timestamp" to LocalDateTime.now().toString()
        )

        val destination = "/topic/analysis/$jobId"
        messagingTemplate.convertAndSend(destination, payload)
    }
}