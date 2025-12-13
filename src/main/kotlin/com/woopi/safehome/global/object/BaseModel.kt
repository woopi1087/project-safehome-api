package com.woopi.safehome.global.`object`

import java.time.LocalDateTime

abstract class BaseModel (
    val isDeleted: Boolean,
    val createdId: Long,
    val createdAt: LocalDateTime,
    val updatedId: Long?,
    val updatedAt: LocalDateTime?,
)