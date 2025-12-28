package com.woopi.safehome.domain._sample.adapter.inbound.web

import com.woopi.safehome.domain._sample.adapter.inbound.web.dto.SampleResponse
import com.woopi.safehome.domain._sample.model.Sample

object SampleDtoMapper {

    fun toResponse(model: Sample): SampleResponse {
        return SampleResponse(
            id = model.id,
            name = model.name,
            code = model.code,
            description = model.description,
            orderNo = model.orderNo,
        )
    }

    fun toResponse(modelList: List<Sample>): List<SampleResponse> {
        return modelList.map { toResponse(it) }
    }

}