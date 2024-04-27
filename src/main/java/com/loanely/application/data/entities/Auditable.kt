package com.loanely.application.data.entities

import com.fasterxml.jackson.annotation.JsonIgnore
import com.fasterxml.jackson.annotation.JsonIgnoreProperties
import org.springframework.data.annotation.CreatedDate
import org.springframework.data.annotation.LastModifiedDate
import org.springframework.data.jpa.domain.support.AuditingEntityListener
import java.util.*
import javax.persistence.*


@MappedSuperclass
@EntityListeners(AuditingEntityListener::class)
@JsonIgnoreProperties(
    value = ["createdAt", "updatedAt"], allowGetters = true
)
abstract class Auditable(
    @JsonIgnore @Temporal(TemporalType.TIMESTAMP) @Column(
        name = "created_at",
        nullable = false,
        updatable = false
    ) @CreatedDate open var createdAt: Date? = Date(),

    @JsonIgnore @Temporal(TemporalType.TIMESTAMP) @Column(
        name = "updated_at",
        nullable = false
    ) @LastModifiedDate open var updatedAt: Date? = Date()
)
