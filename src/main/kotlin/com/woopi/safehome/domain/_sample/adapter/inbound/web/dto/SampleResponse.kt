package com.woopi.safehome.domain._sample.adapter.inbound.web.dto

import io.swagger.v3.oas.annotations.media.Schema

data class SampleResponse(

    @Schema(description = "ID")
    val id: Long,

    @Schema(description = "이름")
    val name: String,

    @Schema(description = "코드")
    val code: String,

    @Schema(description = "설명")
    val description: String?,

    @Schema(description = "정렬 순서")
    val orderNo: Int,
)
