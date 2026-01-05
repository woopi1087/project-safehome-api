package com.woopi.safehome.domain.deed.application.usecase

import com.woopi.safehome.domain.deed.adapter.inbound.web.dto.DeedRequest
import com.woopi.safehome.domain.deed.adapter.inbound.web.dto.DeedResponse
import com.woopi.safehome.domain.deed.application.port.inbound.DeedUseCase
import com.woopi.safehome.domain.deed.application.port.outbound.AnalysisJobPersistencePort
import org.springframework.stereotype.Service
import org.springframework.transaction.annotation.Transactional

@Transactional(readOnly = true)
@Service
class DeedUseCaseImpl (
    private val analysisJobPersistencePort: AnalysisJobPersistencePort
): DeedUseCase {

    override fun analyzeDeed(request: DeedRequest.Analyze): DeedResponse {
    }

}