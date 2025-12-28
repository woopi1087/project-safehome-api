package com.woopi.safehome.domain._sample.adapter.outbound.persistence

import com.woopi.safehome.domain._sample.adapter.outbound.persistence.jpa.SampleEntity
import com.woopi.safehome.domain._sample.model.Sample
import com.woopi.safehome.domain._sample.model.SampleCreate
import com.woopi.safehome.domain._sample.model.SampleUpdate

object SampleEntityMapper {

    fun toModel(entity: SampleEntity): Sample {
        return Sample(
            id = entity.id!!,
            name = entity.name,
            code = entity.code,
            description = entity.description,
            orderNo = entity.orderNo,
        )
    }

    fun toModelList(entities: List<SampleEntity>): List<Sample> {
        return entities.map(SampleEntityMapper::toModel)
    }

    fun toEntity(model: SampleCreate): SampleEntity {
        return SampleEntity(
            name = model.name,
            code = model.code,
            description = model.description,
            orderNo = model.orderNo,
        )
    }

    fun toEntity(model: SampleUpdate): SampleEntity {
        return SampleEntity(
            name = model.name,
            code = model.code,
            description = model.description,
            orderNo = model.orderNo,
        ).apply { id = model.id }
    }

}