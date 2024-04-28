package com.loanely.application.data.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import org.hibernate.annotations.OnDelete
import org.hibernate.annotations.OnDeleteAction
import java.math.BigDecimal
import java.util.*
import javax.persistence.*

@Entity(name = "tbl_loans")
data class Loans(

    var loanDate: Date? = null,
    var dueDate: Date? = null,
    @ManyToOne(fetch = FetchType.LAZY, optional = false) @JoinColumn(
        name = "subscriber_id",
        nullable = false
    ) @OnDelete(action = OnDeleteAction.CASCADE) @JsonIgnore var subscriber: SubscriberEntity? = null,

    var principal: BigDecimal? = null,
    var serviceFee: BigDecimal? = null,
    @ManyToOne
    @JoinColumn(name = "loan_channel_id")
    var loanChannelEntity: LoanChannelEntity? = null,
    @ManyToOne
    @JoinColumn(name = "loan_type_id")
    var loanTypeEntity: LoanTypeEntity? = null,
    @ManyToOne
    @JoinColumn(name = "loan_status_id")
    var loanStatusEntity: LoanStatus? = null
):Auditable() {
    @Id
    @GeneratedValue(strategy = GenerationType.AUTO)
    var loan_id: Long? = 0L
}
