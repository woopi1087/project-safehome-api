package com.woopi.safehome.domain.deed.application.port.inbound

import com.woopi.safehome.domain.deed.adapter.inbound.web.dto.DeedRequest
import com.woopi.safehome.domain.deed.adapter.inbound.web.dto.DeedResponse

interface DeedUseCase {

    /**
     * 분석 작업
     */
    fun analyzeDeed(request: DeedRequest.Analyze): DeedResponse

}