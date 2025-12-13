package com.woopi.safehome.domain._sample.adapter.inbound.web

import com.woopi.safehome.domain._sample.adapter.inbound.web.dto.SampleResponse
import com.woopi.safehome.global.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.GetMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "샘플 API", description = "샘플 API")
@RestController
@RequestMapping("/api/sample")
class SampleInboundWebAdapter {

    @Operation(summary = "샘플 리스트 조회", description = "샘플 리스트 조회")
    @GetMapping
    fun getSampleList (): ApiResponse<List<SampleResponse>> {
        return ApiResponse.success(
            listOf(
                SampleResponse(
                    id = 1,
                    code = "SAMPLE_CODE_1"
                ),
                SampleResponse(
                    id = 2,
                    code = "SAMPLE_CODE_2"
                )
            )
        )
    }
}