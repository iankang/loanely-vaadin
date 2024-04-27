package com.loanely.application.data.entities

import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "loan_channel_entity")
data class LoanChannelEntity(
    var loanChannel:String? = null
):Auditable(){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var loan_channel_id: Long? = 0L
}
