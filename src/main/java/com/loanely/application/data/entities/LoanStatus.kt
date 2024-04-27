package com.loanely.application.data.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "loan_status_entity")
data class LoanStatus(
    var loanStatus:String? = null
){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var loan_status_id: Long? = 0L
}
