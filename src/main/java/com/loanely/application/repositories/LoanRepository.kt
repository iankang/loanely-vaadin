package com.loanely.application.repositories

import com.loanely.application.data.entities.LoanChannelEntity
import com.loanely.application.data.entities.LoanStatus
import com.loanely.application.data.entities.LoanTypeEntity
import com.loanely.application.data.entities.Loans
import com.loanely.application.data.models.response.PrincipalSum
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.security.Principal
import java.util.*

@Repository
interface LoanRepository:JpaRepository<Loans,Long> {

    fun countAllByLoanChannelEntity(channelEntity: LoanChannelEntity):Long

    fun countAllByLoanTypeEntity(loanTypeEntity: LoanTypeEntity):Long

    fun countAllByLoanStatusEntity(loanStatus: LoanStatus):Long

    @Query("SELECT loanDate as loanDate, sum(principal) as principalSum from tbl_loans group by 1 order by 1")
    fun getDateAndPrincipalSum():List<PrincipalSum>
}