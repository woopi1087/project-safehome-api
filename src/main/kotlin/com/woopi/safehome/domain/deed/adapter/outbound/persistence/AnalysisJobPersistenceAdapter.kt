package com.woopi.safehome.domain.deed.adapter.outbound.persistence

import com.woopi.safehome.domain.deed.adapter.outbound.persistence.jpa.AnalysisJobRepository
import com.woopi.safehome.domain.deed.application.port.outbound.AnalysisJobPersistencePort
import com.woopi.safehome.domain.deed.model.AnalysisJob
import org.springframework.stereotype.Component

@Component
class AnalysisJobPersistenceAdapter(
    private val analysisJobRepository: AnalysisJobRepository
) : AnalysisJobPersistencePort {

    override fun save(analysisJobCreate: AnalysisJob.Create): AnalysisJob.Data {
        return analysisJobRepository.save(
            AnalysisJobEntityMapper.toEntity(analysisJobCreate)
        ).let { AnalysisJobEntityMapper.toModel(it) }
    }

}