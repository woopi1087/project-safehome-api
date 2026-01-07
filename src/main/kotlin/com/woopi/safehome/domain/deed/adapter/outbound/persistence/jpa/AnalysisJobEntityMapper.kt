package com.woopi.safehome.domain.deed.adapter.outbound.persistence.jpa

import com.woopi.safehome.domain.deed.model.AnalysisJob

object AnalysisJobEntityMapper {

    fun toModel(entity: AnalysisJobEntity): AnalysisJob.Data {
        return AnalysisJob.Data(
            id = entity.id!!,
            jobId = entity.jobId,
            fileName = entity.fileName,
            fileSize = entity.fileSize,
            status = entity.status,
            result = entity.result,
            description = entity.description,
        )
    }

    fun toEntity(createModel: AnalysisJob.Create): AnalysisJobEntity {
        return AnalysisJobEntity(
            jobId = createModel.jobId,
            fileName = createModel.fileName,
            fileSize = createModel.fileSize,
            status = createModel.status,
            result = createModel.result,
            description = createModel.description,
        )
    }

}