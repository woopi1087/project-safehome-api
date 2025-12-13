package com.woopi.safehome.domain._sample.adapter.outbound.persistence.jpa

import com.woopi.safehome.global.`object`.BaseEntity
import jakarta.persistence.*

@Entity
@Table(name = "sample_details")
class SampleDetailEntity(

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_id", nullable = false)
    var sample: SampleEntity,

    @Column(name = "detail_value")
    var detailValue: String? = null,

): BaseEntity()