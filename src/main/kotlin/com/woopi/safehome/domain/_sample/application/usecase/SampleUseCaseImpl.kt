package com.woopi.safehome.domain._sample.application.usecase

import com.woopi.safehome.domain._sample.adapter.inbound.web.SampleDtoMapper
import com.woopi.safehome.domain._sample.adapter.inbound.web.dto.SampleRequest
import com.woopi.safehome.domain._sample.adapter.inbound.web.dto.SampleResponse
import com.woopi.safehome.domain._sample.application.port.inbound.SampleUseCase
import com.woopi.safehome.domain._sample.application.port.outbound.SamplePersistencePort
import com.woopi.safehome.domain.analysis.application.port.outbound.AnalysisProgressPort
import com.woopi.safehome.global.enums.AnalysisStep
import com.woopi.safehome.global.enums.JobStatus
import com.woopi.safehome.global.exception.BusinessException
import com.woopi.safehome.global.exception.ErrorCode
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional
import java.util.UUID

@Transactional(readOnly = true)
@Service
class SampleUseCaseImpl(
    private val samplePersistencePort: SamplePersistencePort,
    private val analysisProgressPort: AnalysisProgressPort
): SampleUseCase {

    override fun getSampleList(request: SampleRequest.Search): List<SampleResponse> {
        return SampleDtoMapper.toResponse(samplePersistencePort.findAllSample())
    }

    override fun getSampleDetails(id: Long): SampleResponse {
        // 없으면 에러 처리 필요
        val sample = samplePersistencePort.findSampleById(id)
            ?: throw BusinessException(ErrorCode.NOT_FOUND)

        return SampleDtoMapper.toResponse(sample)
    }

    @Transactional
    override fun createSample(request: SampleRequest.Create): SampleResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun updateSample(request: SampleRequest.Update): SampleResponse {
        TODO("Not yet implemented")
    }

    @Transactional
    override fun deleteSample(id: Long): SampleResponse {
        TODO("Not yet implemented")
    }


    override fun websocketSample() {
        val jobId = UUID.randomUUID().toString()

        // 1. PDF 파싱 시작
        analysisProgressPort.notifyStep(
            jobId = jobId,
            status = JobStatus.IN_PROGRESS,
            step = AnalysisStep.PDF_PARSING,
            message = "첨부된 파일을 분석중이에요"
        )

        // ---- PDF 파싱 로직 (지금은 생략 / 하드코딩) ----
        Thread.sleep(1000)

        // 2. LLM 분석
        analysisProgressPort.notifyStep(
            jobId = jobId,
            status = JobStatus.IN_PROGRESS,
            step = AnalysisStep.LLM_ANALYSIS,
            message = "AI가 등본을 분석중이에요"
        )

        Thread.sleep(1000)

        // 3. 이후 처리
        analysisProgressPort.notifyStep(
            jobId = jobId,
            status = JobStatus.IN_PROGRESS,
            step = AnalysisStep.POST_PROCESSING,
            message = "분석한 내용을 정리중이에요"
        )

        // 4. 완료
        analysisProgressPort.notifyStep(
            jobId = jobId,
            status = JobStatus.COMPLETED,
            step = null,
            message = "완료 됐습니다!"
        )

    }


}