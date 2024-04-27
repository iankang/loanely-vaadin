package com.loanely.application.data.entities

import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import java.sql.Date
import javax.persistence.*

@Entity(name = "tbl_repay")
data class Repayment(
    var repayDate: Date? = null,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(
        name = "subscriber_id"
    ) @OnDelete(action = OnDeleteAction.CASCADE) var subscriber: SubscriberEntity? = null,
    @ManyToOne(fetch = FetchType.LAZY) @JoinColumn(
        name = "loan_id"
    ) @OnDelete(action = OnDeleteAction.CASCADE) var loan: Loans? = null,
    @ManyToOne @JoinColumn(name = "repayment_type_id") var repaymentType: RepaymentTypeEntity? = null,

    var amountPaid: BigDecimal? = null,
    var repaymentTransactionRef: String? = null
) : Auditable() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var repayment_id: Long? = 0L
}
