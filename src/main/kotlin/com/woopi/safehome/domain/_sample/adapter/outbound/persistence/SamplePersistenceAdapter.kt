package com.woopi.safehome.domain._sample.adapter.outbound.persistence

import com.woopi.safehome.domain._sample.adapter.outbound.persistence.jpa.SampleRepository
import com.woopi.safehome.domain._sample.application.port.outbound.SamplePersistencePort
import com.woopi.safehome.domain._sample.model.Sample
import com.woopi.safehome.domain._sample.model.SampleCreate
import org.springframework.stereotype.Component

@Component
class SamplePersistenceAdapter(
    private val sampleRepository: SampleRepository
) : SamplePersistencePort {

    override fun findAllSample(): List<Sample> {
        return sampleRepository.findByIsDeletedFalse()
            .let { SampleEntityMapper.toModelList(it) }
    }

    override fun findSampleById(id: Long): Sample? {
        return sampleRepository.findByIdAndIsDeletedFalse(id)
            ?.let { SampleEntityMapper.toModel(it) }
    }

    override fun saveSample(sampleCreate: SampleCreate): Sample {
        return sampleRepository.save(SampleEntityMapper.toEntity(sampleCreate))
            .let { SampleEntityMapper.toModel(it) }
    }

}