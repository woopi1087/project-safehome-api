package com.woopi.safehome.domain.deed.application.usecase

import com.woopi.safehome.domain.deed.adapter.inbound.web.dto.DeedDtoMapper
import com.woopi.safehome.domain.deed.adapter.inbound.web.dto.DeedRequest
import com.woopi.safehome.domain.deed.adapter.inbound.web.dto.DeedResponse
import com.woopi.safehome.domain.deed.application.port.inbound.DeedUseCase
import com.woopi.safehome.domain.deed.application.port.outbound.AnalysisJobPersistencePort
import com.woopi.safehome.domain.deed.model.AnalysisJob
import com.woopi.safehome.global.enums.JobStatus
import org.slf4j.LoggerFactory
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional(readOnly = true)
@Service
class DeedUseCaseImpl (
    private val analysisJobPersistencePort: AnalysisJobPersistencePort
): DeedUseCase {

    private val logger = LoggerFactory.getLogger(javaClass)

    override fun analyzeDeed(request: DeedRequest.Analyze): DeedResponse {

        val file = request.file

        logger.info("📤 분석 요청 - fileName: ${file.originalFilename}, size: ${file.size}")

        // 파일 검증
        require(!file.isEmpty) { "파일이 비어있습니다" }
        require(file.contentType == "application/pdf") { "PDF 파일만 가능합니다" }
        require(file.size <= 50 * 1024 * 1024) { "파일은 50MB 이하여야 합니다" }

        val jobId = UUID.randomUUID().toString()

        val job = AnalysisJob.Create(
            jobId = jobId,
            fileName = file.originalFilename ?: "unknown.pdf",
            fileSize = file.size,
            status = JobStatus.PENDING,
        )

        val savedJob = analysisJobPersistencePort.save(job)

        logger.info("✅ 분석 완료 - jobId: $jobId")

        return DeedDtoMapper.toResponse(savedJob)
    }

}