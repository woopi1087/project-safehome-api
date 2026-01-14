package com.woopi.safehome.domain.analysis.adapter.outbound.sse

import com.woopi.safehome.domain.analysis.application.port.outbound.AnalysisNotifierPort
import com.woopi.safehome.global.enums.AnalysisStep
import com.woopi.safehome.global.enums.JobStatus
import org.springframework.stereotype.Component
import org.springframework.web.servlet.mvc.method.annotation.SseEmitter
import java.time.LocalDateTime
import java.util.concurrent.ConcurrentHashMap

@Component
class AnalysisSseNotifier: AnalysisNotifierPort {

    private val emitters = ConcurrentHashMap<String, SseEmitter>()

    fun createEmitter(jobId: String): SseEmitter {
        val emitter = SseEmitter(300_000L) // 5분 타임아웃

        emitter.onCompletion { emitters.remove(jobId) }
        emitter.onTimeout { emitters.remove(jobId) }
        emitter.onError { emitters.remove(jobId) }

        emitters[jobId] = emitter
        return emitter
    }

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

        emitters[jobId]?.let { emitter ->
            emitter.send(SseEmitter.event().data(payload))

            // 완료되면 명시적으로 종료
            if (status == JobStatus.COMPLETED) {
                emitter.complete()
                emitters.remove(jobId)
            }
        }
    }

}