package com.loanely.application.repositories

import com.loanely.application.data.entities.LoanTypeEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanTypeRepository:JpaRepository<LoanTypeEntity,Long> {
}