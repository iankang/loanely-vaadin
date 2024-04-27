package com.loanely.application.data.entities

import com.loanely.application.data.enums.Gender
import com.loanely.application.data.enums.Qualification
import java.math.BigDecimal
import javax.persistence.*

@Entity(name = "subscriber_tbl")
data class SubscriberEntity(
    @Column(name = "phone_number") var phoneNumber: String? = null,
    var fistName: String? = null,
    var lastName: String? = null,
    var limitAmount: BigDecimal? = null,
    var borrowableAmount: BigDecimal? = null,
    var gender: Gender? = null,
    var qualification: Qualification? = null
) : Auditable() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var id: Long? = null
}
