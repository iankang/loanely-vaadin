package com.loanely.application.services

import com.loanely.application.data.entities.RepaymentTypeEntity
import com.loanely.application.repositories.RepaymentTypeRepository
import org.springframework.stereotype.Service
import org.vaadin.crudui.crud.CrudListener

@Service
class RepaymentTypeService(
    private val repaymentTypeRepository: RepaymentTypeRepository
): CrudListener<RepaymentTypeEntity> {
    override fun findAll(): MutableCollection<RepaymentTypeEntity> {
        return repaymentTypeRepository.findAll()
    }

    override fun delete(p0: RepaymentTypeEntity?) {
        if (p0 != null) {
            repaymentTypeRepository.delete(p0)
        }
    }

    override fun update(p0: RepaymentTypeEntity?): RepaymentTypeEntity {
       return repaymentTypeRepository.save(p0)!!
    }

    override fun add(p0: RepaymentTypeEntity?): RepaymentTypeEntity {
        return repaymentTypeRepository.save(p0)!!
    }

    fun addAll(repaymentTypes:List<RepaymentTypeEntity>): MutableList<RepaymentTypeEntity> {
        return repaymentTypeRepository.saveAll(repaymentTypes)
    }

    fun count():Long{
        return repaymentTypeRepository.count()
    }
}