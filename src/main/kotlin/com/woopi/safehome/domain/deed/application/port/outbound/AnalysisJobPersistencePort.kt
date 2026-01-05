package com.woopi.safehome.domain.deed.application.port.outbound

import com.woopi.safehome.domain.deed.model.AnalysisJob

interface AnalysisJobPersistencePort {

    /**
     * 분석 작업 저장
     */
    fun save(analysisJobCreate: AnalysisJob.Create): AnalysisJob.Data

}