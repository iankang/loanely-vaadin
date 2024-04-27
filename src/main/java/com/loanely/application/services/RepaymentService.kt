package com.loanely.application.services

import com.loanely.application.data.entities.Repayment
import com.loanely.application.repositories.RepaymentRepository
import org.springframework.stereotype.Service
import org.vaadin.crudui.crud.CrudListener

@Service
class RepaymentService(
    private val repaymentRepository: RepaymentRepository
): CrudListener<Repayment> {
    override fun findAll(): MutableCollection<Repayment> {
        return repaymentRepository.findAll()
    }

    override fun delete(p0: Repayment?) {
        if (p0 != null) {
            repaymentRepository.delete(p0)
        }
    }

    override fun update(p0: Repayment?): Repayment {
        return repaymentRepository.save(p0)!!
    }

    override fun add(p0: Repayment?): Repayment {
        return repaymentRepository.save(p0)!!
    }

    fun count():Long{
        return repaymentRepository.count()
    }
}