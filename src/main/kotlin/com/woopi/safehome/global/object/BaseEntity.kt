package com.woopi.safehome.global.`object`

import jakarta.persistence.*
import java.time.LocalDateTime

@MappedSuperclass
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        protected set

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false
        protected set

    @Column(name = "created_id", nullable = false, updatable = false)
    var createdId: Long = 0
        protected set

    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime = LocalDateTime.now()
        protected set

    @Column(name = "updated_id")
    var updatedId: Long? = null
        protected set

    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
        protected set

    fun delete() {
        this.isDeleted = true
    }

}