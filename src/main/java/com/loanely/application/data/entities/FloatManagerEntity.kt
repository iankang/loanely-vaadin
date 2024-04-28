package com.loanely.application.data.entities

import java.math.BigDecimal
import javax.persistence.Entity
import javax.persistence.GeneratedValue
import javax.persistence.GenerationType
import javax.persistence.Id

@Entity(name = "float_manager")
data class FloatManagerEntity(
    var balance:BigDecimal? = null,

):Auditable(){
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var float_id: Long? = 0L
}
