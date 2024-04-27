package com.loanely.application.services

import com.loanely.application.data.entities.Loans
import com.loanely.application.repositories.LoanRepository
import org.springframework.stereotype.Service
import org.vaadin.crudui.crud.CrudListener

@Service
class LoanService(
    private val loanRepository: LoanRepository
) : CrudListener<Loans> {
    override fun findAll(): MutableCollection<Loans> {
        return  loanRepository.findAll()
    }

    override fun delete(p0: Loans?) {
        if (p0 != null) {
            loanRepository.delete(p0)
        }
    }

    override fun update(p0: Loans?): Loans {
        return loanRepository.save(p0)!!
    }

    override fun add(p0: Loans?): Loans {
        return loanRepository.save(p0)!!
    }

    fun count(): Long {
        return loanRepository.count()
    }
}