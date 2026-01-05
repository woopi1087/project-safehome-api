package com.woopi.safehome.domain.deed.adapter.outbound.persistence.jpa

import com.woopi.safehome.global.enums.AnalysisJobStatus
import com.woopi.safehome.global.`object`.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.EnumType
import jakarta.persistence.Enumerated
import jakarta.persistence.Table

@Entity
@Table(name = "analysis_jobs")
class AnalysisJobEntity(

    @Column(name = "job_id" )
    var jobId: String,

    @Column(name = "file_name" )
    var fileName: String,

    @Column(name = "file_size" )
    var fileSize: Long,

    @Column(name = "status" )
    @Enumerated(EnumType.STRING)
    var status: AnalysisJobStatus,

    @Column(name = "result" )
    var result: String? = null,

    @Column(name = "description" )
    var description: String? = null,

    ) : BaseEntity() {

}