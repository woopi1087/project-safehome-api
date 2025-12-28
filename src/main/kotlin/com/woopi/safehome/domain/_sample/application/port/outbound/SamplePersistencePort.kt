package com.woopi.safehome.domain._sample.application.port.outbound

import com.woopi.safehome.domain._sample.model.Sample
import com.woopi.safehome.domain._sample.model.SampleCreate

interface SamplePersistencePort {

    /**
     * 샘플 리스트 조회
     */
    fun findAllSample(): List<Sample>

    /**
     * 샘플 상세 조회
     */
    fun findSampleById(id: Long): Sample?

    /**
     * 샘플 저장
     */
    fun saveSample(sampleCreate: SampleCreate): Sample

}