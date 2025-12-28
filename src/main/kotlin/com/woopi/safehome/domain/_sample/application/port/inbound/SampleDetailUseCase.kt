package com.woopi.safehome.domain._sample.application.port.inbound

import com.woopi.safehome.domain._sample.adapter.inbound.web.dto.SampleDetailRequest
import com.woopi.safehome.domain._sample.adapter.inbound.web.dto.SampleDetailResponse

interface SampleDetailUseCase {

    /**
     * 샘플 상세 리스트 조회
     */
    fun getSampleDetailList(request: SampleDetailRequest.Search): List<SampleDetailResponse>

    /**
     * 샘플 상세 상세 조회
     */
    fun getSampleDetailDetails(id: Long): SampleDetailResponse
//
//    /**
//     * 샘플 상세 생성
//     */
//    fun createSampleDetail(request: SampleDetailRequest.Create): SampleDetailResponse
//
//    /**
//     * 샘플 상세 수정
//     */
//    fun updateSampleDetail(request: SampleDetailRequest.Update): SampleDetailResponse
//
//    /**
//     * 샘플 상세 삭제
//     */
//    fun deleteSampleDetail(id: Long): SampleDetailResponse

}