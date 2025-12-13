package com.woopi.safehome.domain._sample.adapter.outbound.persistence.jpa

import jakarta.persistence.*
import java.time.LocalDateTime

@Entity
@Table(name = "sample_details")
class SampleDetailEntity (

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "sample_id", nullable = false)
    var sample: SampleEntity,

    @Column(name = "detail_value")
    var detailValue: String? = null,

    var isDeleted: Boolean = false,
    var createdId: Long,
    var createdAt: LocalDateTime,
    var updatedId: Long? = null,
    var updatedAt: LocalDateTime? = null
)