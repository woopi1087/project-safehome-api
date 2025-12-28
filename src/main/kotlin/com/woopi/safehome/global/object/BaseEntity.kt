package com.woopi.safehome.global.`object`

import jakarta.persistence.*
import org.springframework.data.annotation.CreatedBy
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedBy
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.time.LocalDateTime

@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
abstract class BaseEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    var id: Long? = null
        internal set

    @Column(name = "is_deleted", nullable = false)
    var isDeleted: Boolean = false
        protected set

    @CreatedBy
    @Column(name = "created_id", nullable = false, updatable = false)
    var createdId: Long? = null
        protected set

    @CreatedDate
    @Column(name = "created_at", nullable = false, updatable = false)
    var createdAt: LocalDateTime? = null
        protected set

    @LastModifiedBy
    @Column(name = "updated_id")
    var updatedId: Long? = null
        protected set

    @LastModifiedDate
    @Column(name = "updated_at")
    var updatedAt: LocalDateTime? = null
        protected set

    fun delete() {
        this.isDeleted = true
    }

}