package com.woopi.safehome.domain._sample.adapter.outbound.persistence.jpa

import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface SampleDetailRepository: JpaRepository<SampleDetailEntity, Long> {
}