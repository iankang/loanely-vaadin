package com.loanely.application.repositories

import com.loanely.application.data.entities.Repayment
import com.loanely.application.data.entities.RepaymentTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RepaymentRepository:JpaRepository<Repayment,Long> {

    fun countAllByRepaymentType(repaymentTypeEntity: RepaymentTypeEntity):Long


}