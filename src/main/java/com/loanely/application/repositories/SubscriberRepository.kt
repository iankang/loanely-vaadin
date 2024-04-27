package com.loanely.application.repositories

import com.loanely.application.data.entities.SubscriberEntity
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.stereotype.Repository
import java.util.concurrent.Flow.Subscriber

@Repository
interface SubscriberRepository:JpaRepository<SubscriberEntity,Long> {
}