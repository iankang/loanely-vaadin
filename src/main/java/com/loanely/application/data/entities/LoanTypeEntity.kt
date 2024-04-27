package com.loanely.application.data.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "loan_type_entity")
data class LoanTypeEntity(
    var loanType:String? = null
):Auditable(){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var loan_type_id: Long? = 0L
}
