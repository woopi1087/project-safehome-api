package com.woopi.safehome.domain.deed.model

import com.woopi.safehome.global.enums.AnalysisJobStatus

object AnalysisJob {

    data class Query(
        val id: Long,
        val status: AnalysisJobStatus
    )

    data class Create(
        val jobId: String,
        val fileName: String,
        val fileSize: Long,
        val status: AnalysisJobStatus,
        val result: String? = null,
        val description: String? = null
    )

    data class Update(
        val id: Long,
        val status: AnalysisJobStatus,
        val result: String? = null,
        val description: String? = null
    )

    data class Data(
        val id: Long,
        val jobId: String,
        val fileName: String,
        val fileSize: Long,
        val status: AnalysisJobStatus,
        val result: String? = null,
        val description: String? = null
    )

}
