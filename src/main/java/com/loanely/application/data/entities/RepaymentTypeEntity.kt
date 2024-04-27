package com.loanely.application.data.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "repayment_type")
data class RepaymentTypeEntity(
    var repaymentType:String? = null
):Auditable(){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var repayment_type_id: Long? = 0L
}
