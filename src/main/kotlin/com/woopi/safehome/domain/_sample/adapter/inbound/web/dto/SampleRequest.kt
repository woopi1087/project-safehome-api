package com.woopi.safehome.domain._sample.adapter.inbound.web.dto

import io.swagger.v3.oas.annotations.media.Schema
import jakarta.validation.constraints.NotBlank
import jakarta.validation.constraints.NotNull

object SampleRequest {

    data class Search(
        @Schema(description = "이름", example = "샘플1")
        val name: String?,

        @Schema(description = "코드", example = "SAMPLE_001")
        val code: String?,
    )

    data class Create(
        @Schema(description = "이름", example = "샘플1")
        @field:NotBlank(message = "이름은 필수 입니다.")
        val name: String,

        @Schema(description = "코드", example = "SAMPLE_001")
        @field:NotBlank(message = "코드는 필수 입니다.")
        val code: String,

        @Schema(description = "설명", example = "샘플 설명")
        val description: String?,

        @Schema(description = "정렬 순서", example = "1")
        val orderNo: Int,
    )

    data class Update(
        @Schema(description = "샘플 ID", example = "1")
        @field:NotNull(message = "샘플 ID는 필수 입니다.")
        val id: Long,

        @Schema(description = "이름", example = "샘플1")
        @field:NotBlank(message = "이름은 필수 입니다.")
        val name: String,

        @Schema(description = "코드", example = "SAMPLE_001")
        @field:NotBlank(message = "코드는 필수 입니다.")
        val code: String,

        @Schema(description = "설명", example = "샘플 설명")
        val description: String?,

        @Schema(description = "정렬 순서", example = "1")
        val orderNo: Int,
    )

}
