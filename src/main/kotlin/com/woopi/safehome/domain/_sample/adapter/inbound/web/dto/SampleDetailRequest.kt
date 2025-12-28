package com.woopi.safehome.domain._sample.adapter.inbound.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotNull

object SampleDetailRequest {

    data class Search(
        @Schema(description = "상세 값", example = "상세 값_1", required = false)
        val detailValue: String?
    )

    data class Create(
        @Schema(description = "샘플 ID", example = "1", required = true)
        @NotNull
        val sampleId: Long,

        @Schema(description = "상세 값", example = "상세 값_1", required = false)
        val detailValue: String
    )

    data class Update(
        @Schema(description = "샘플 ID", example = "1", required = true)
        @NotNull
        val id: Long,

        @Schema(description = "상세 값", example = "상세 값_1", required = false)
        val detailValue: String
    )

}
