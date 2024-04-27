package com.loanely.application.services

import com.loanely.application.data.entities.SubscriberEntity
import com.loanely.application.repositories.SubscriberRepository
import org.springframework.stereotype.Service
import org.vaadin.crudui.crud.CrudListener

@Service
class SubscriberService(
    private val subscriberRepository: SubscriberRepository
): CrudListener<SubscriberEntity> {
    override fun findAll(): MutableCollection<SubscriberEntity> {
        return subscriberRepository.findAll()
    }

    override fun delete(p0: SubscriberEntity?) {
        if (p0 != null) {
            subscriberRepository.delete(p0)
        }
    }

    override fun update(p0: SubscriberEntity?): SubscriberEntity {
        return subscriberRepository.save(p0)!!
    }

    override fun add(p0: SubscriberEntity?): SubscriberEntity {
        return subscriberRepository.save(p0)!!
    }

    fun count():Long{
        return subscriberRepository.count()
    }

    fun addAll(subLIst:List<SubscriberEntity>): MutableList<SubscriberEntity> {
        return subscriberRepository.saveAll(subLIst)
    }
}