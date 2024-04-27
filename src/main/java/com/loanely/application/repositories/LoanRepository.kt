package com.loanely.application.repositories

import com.loanely.application.data.entities.Loans
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanRepository:JpaRepository<Loans,Long> {
}