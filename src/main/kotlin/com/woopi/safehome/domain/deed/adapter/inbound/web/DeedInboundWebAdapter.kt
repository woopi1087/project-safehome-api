package com.woopi.safehome.domain.deed.adapter.inbound.web

import com.woopi.safehome.domain.deed.adapter.inbound.web.dto.DeedRequest
import com.woopi.safehome.domain.deed.adapter.inbound.web.dto.DeedResponse
import com.woopi.safehome.domain.deed.application.port.inbound.DeedUseCase
import com.woopi.safehome.global.response.ApiResponse
import io.swagger.v3.oas.annotations.Operation
import io.swagger.v3.oas.annotations.tags.Tag
import org.springframework.web.bind.annotation.PostMapping
import org.springframework.web.bind.annotation.RequestMapping
import org.springframework.web.bind.annotation.RestController

@Tag(name = "등기부등본 API", description = "등기부등본 API")
@RestController
@RequestMapping("/api/deed")
class DeedInboundWebAdapter(
    private val deedUseCase: DeedUseCase
) {

    @Operation(summary = "등기부등본 분석", description = "등기부등본 분석")
    @PostMapping
    fun analyzeDeed(request: DeedRequest.Analyze): ApiResponse<DeedResponse> {
        return ApiResponse.success(deedUseCase.analyzeDeed(request))
    }

}