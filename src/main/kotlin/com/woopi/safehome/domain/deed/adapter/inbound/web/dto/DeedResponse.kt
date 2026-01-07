package com.woopi.safehome.domain.deed.adapter.inbound.web.dto

data class DeedResponse(
    val id: Long,
    val jobId: String,
    val description: String?,
)
