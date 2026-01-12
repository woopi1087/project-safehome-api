package com.woopi.safehome.domain.analysis.application.port.outbound

import com.woopi.safehome.global.enums.AnalysisStep
import com.woopi.safehome.global.enums.JobStatus

interface AnalysisProgressPort {
    fun notifyStep(
        jobId: String,
        status: JobStatus,
        step: AnalysisStep?,
        message: String
    )
}