package com.loanely.application.repositories

import com.loanely.application.data.entities.LoanChannelEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanChannelRepository:JpaRepository<LoanChannelEntity,Long> {

    fun findByLoanChannel(loanChannelService:String):LoanChannelEntity
}