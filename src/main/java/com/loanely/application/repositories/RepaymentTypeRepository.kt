package com.loanely.application.repositories

import com.loanely.application.data.entities.RepaymentTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface RepaymentTypeRepository:JpaRepository<RepaymentTypeEntity,Long> {

    fun findByRepaymentType(repaymentType:String):RepaymentTypeEntity
}