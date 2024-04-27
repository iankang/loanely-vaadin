package com.loanely.application.services

import com.loanely.application.data.entities.LoanChannelEntity
import com.loanely.application.repositories.LoanChannelRepository
import com.vaadin.flow.component.crud.Crud
import org.springframework.stereotype.Service
import org.vaadin.crudui.crud.CrudListener

@Service
class LoanChannelService(
    private val loanChannelRepository: LoanChannelRepository
): CrudListener<LoanChannelEntity> {
    override fun findAll(): MutableCollection<LoanChannelEntity> {
        return loanChannelRepository.findAll()
    }

    override fun delete(p0: LoanChannelEntity?) {
        if (p0 != null) {
            loanChannelRepository.delete(p0)
        }
    }

    override fun update(p0: LoanChannelEntity?): LoanChannelEntity {
        return loanChannelRepository.save(p0)!!
    }

    override fun add(p0: LoanChannelEntity?): LoanChannelEntity {
        return loanChannelRepository.save(p0)!!
    }

    fun count():Long{
        return loanChannelRepository.count()
    }

    fun addAll(loanChannelList:List<LoanChannelEntity>): MutableList<LoanChannelEntity> {
        return loanChannelRepository.saveAll(loanChannelList)
    }
}