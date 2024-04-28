package com.loanely.application.repositories

import com.loanely.application.data.entities.FloatManagerEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository

@Repository
interface FloatManagerRepository:JpaRepository<FloatManagerEntity,Long> {
}