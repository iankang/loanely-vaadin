package com.loanely.application.services

import com.loanely.application.data.entities.LoanTypeEntity
import com.loanely.application.repositories.LoanTypeRepository
import org.springframework.stereotype.Service
import org.vaadin.crudui.crud.CrudListener

@Service
class LoanTypeService(
    private val loanTypeRepository: LoanTypeRepository
): CrudListener<LoanTypeEntity> {
    override fun findAll(): MutableCollection<LoanTypeEntity> {
        return loanTypeRepository.findAll()
    }

    override fun delete(p0: LoanTypeEntity?) {
        if (p0 != null) {
            loanTypeRepository.delete(p0)
        }
    }

    override fun update(p0: LoanTypeEntity?): LoanTypeEntity {
        return  loanTypeRepository.save(p0)!!
    }

    override fun add(p0: LoanTypeEntity?): LoanTypeEntity {
        return loanTypeRepository.save(p0)!!
    }

    fun count():Long{
        return loanTypeRepository.count()
    }

    fun addAll(loanTypes:List<LoanTypeEntity>): MutableList<LoanTypeEntity> {
        return loanTypeRepository.saveAll(loanTypes)
    }
}