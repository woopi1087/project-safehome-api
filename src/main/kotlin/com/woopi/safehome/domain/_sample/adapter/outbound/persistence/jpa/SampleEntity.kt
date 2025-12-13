package com.woopi.safehome.domain._sample.adapter.outbound.persistence.jpa

import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.GeneratedValue
import jakarta.persistence.GenerationType
import jakarta.persistence.Id
import jakarta.persistence.Table
import java.time.LocalDateTime

@Entity
@Table(name = "samples")
class SampleEntity(

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null,

    var name: String,

    var code: String,

    var description: String? = null,

    @Column(name = "order_no")
    var orderNo: Int = 0,

    var isDeleted: Boolean = false,
    var createdId: Long,
    var createdAt: LocalDateTime,
    var updatedId: Long? = null,
    var updatedAt: LocalDateTime? = null
)
