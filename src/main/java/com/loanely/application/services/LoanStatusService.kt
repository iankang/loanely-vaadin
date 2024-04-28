package com.loanely.application.services

import com.loanely.application.data.entities.LoanStatus
import com.loanely.application.repositories.LoanStatusRepository
import org.springframework.stereotype.Service
import org.vaadin.crudui.crud.CrudListener

@Service
class LoanStatusService(
    private val loanStatusRepository: LoanStatusRepository
): CrudListener<LoanStatus> {
    override fun findAll(): MutableCollection<LoanStatus> {
        return loanStatusRepository.findAll()
    }

    override fun delete(p0: LoanStatus?) {
        if (p0 != null) {
            loanStatusRepository.delete(p0)
        }
    }

    override fun update(p0: LoanStatus?): LoanStatus {
        return loanStatusRepository.save(p0)!!
    }

    override fun add(p0: LoanStatus?): LoanStatus {
        return loanStatusRepository.save(p0)!!
    }

    fun count():Long{
        return loanStatusRepository.count()
    }

    fun addAll(stati:List<LoanStatus>): MutableList<LoanStatus> {
        return loanStatusRepository.saveAll(stati)
    }

    fun findStatusByStatusName(statusName:String):LoanStatus{
        return loanStatusRepository.findByLoanStatus(statusName);
    }
}