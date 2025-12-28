package com.woopi.safehome.domain._sample.adapter.inbound.web.dto

import io.swagger.v3.oas.annotations.media.Schema

data class SampleDetailResponse(

    @Schema(description = "ID")
    val id: Long,

    @Schema(description = "샘플 정보")
    val sample: SampleResponse,

    @Schema(description = "상세 값")
    val detailValue: String
)
