package com.woopi.safehome.domain._sample.adapter.outbound.persistence.jpa

import io.kotest.core.spec.style.BehaviorSpec
import io.kotest.matchers.comparables.shouldBeGreaterThan
import io.kotest.matchers.nulls.shouldNotBeNull
import io.kotest.matchers.shouldBe
import org.springframework.beans.factory.annotation.Autowired
import org.springframework.boot.test.context.SpringBootTest

@SpringBootTest
class SampleEntityAuditTest(
    @Autowired
    private val sampleRepository: SampleRepository
) : BehaviorSpec({

    Given("SampleEntity를 생성할 때") {

        When("처음 저장하면") {
            val entity = SampleEntity(
                name = "sample",
                code = "S001"
            )

            val saved = sampleRepository.save(entity)

            Then("created audit 정보가 자동으로 채워진다") {
                saved.id.shouldNotBeNull()

                saved.createdId shouldBe 1L
                saved.createdAt.shouldNotBeNull()

                saved.updatedId shouldBe 1L
                saved.updatedAt.shouldNotBeNull()
            }

            When("값을 수정해서 다시 저장하면") {
                val beforeUpdatedAt = saved.updatedAt

                saved.changeName("sample-modified")
                val updated = sampleRepository.save(saved)

                Then("updated audit 정보가 갱신된다") {
                    updated.updatedId shouldBe 1L
                    updated.updatedAt.shouldNotBeNull()
                    updated.updatedAt!! shouldBeGreaterThan  beforeUpdatedAt!!
                }
            }
        }
    }
})
