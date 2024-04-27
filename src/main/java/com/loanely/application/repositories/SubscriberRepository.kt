package com.loanely.application.repositories

import com.loanely.application.data.entities.SubscriberEntity
import com.loanely.application.data.enums.Gender
import com.loanely.application.data.enums.Qualification
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.Flow.Subscriber

@Repository
interface SubscriberRepository:JpaRepository<SubscriberEntity,Long> {

    fun countAllByQualification(qualification: Qualification):Long

    fun countAllByGender(gender: Gender):Long
}