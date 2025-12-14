package com.woopi.safehome.domain._sample.adapter.outbound.persistence.jpa

import com.woopi.safehome.global.`object`.BaseEntity
import jakarta.persistence.Column
import jakarta.persistence.Entity
import jakarta.persistence.Table

@Entity
@Table(name = "samples")
class SampleEntity(

    @Column(name = "name")
    var name: String,

    @Column(name = "code")
    var code: String,

    @Column(name = "description")
    var description: String? = null,

    @Column(name = "order_no")
    var orderNo: Int = 0,

): BaseEntity() {

    fun changeName(name: String) {
        require(name.isNotBlank())
        this.name = name
    }

}
