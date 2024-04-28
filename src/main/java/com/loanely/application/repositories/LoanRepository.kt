package com.loanely.application.repositories

import com.loanely.application.data.entities.LoanChannelEntity
import com.loanely.application.data.entities.LoanStatus
import com.loanely.application.data.entities.LoanTypeEntity
import com.loanely.application.data.entities.Loans
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanRepository:JpaRepository<Loans,Long> {

    fun countAllByLoanChannelEntity(channelEntity: LoanChannelEntity):Long

    fun countAllByLoanTypeEntity(loanTypeEntity: LoanTypeEntity):Long

    fun countAllByLoanStatusEntity(loanStatus: LoanStatus):Long
}