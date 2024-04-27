package com.loanely.application.repositories

import com.loanely.application.data.entities.LoanStatus
import com.sun.jna.platform.win32.WinDef.LONG
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface LoanStatusRepository:JpaRepository<LoanStatus,Long> {
}