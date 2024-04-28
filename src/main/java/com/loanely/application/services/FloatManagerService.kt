package com.loanely.application.services

import com.loanely.application.data.entities.FloatManagerEntity
import com.loanely.application.repositories.FloatManagerRepository
import org.springframework.stereotype.Service
import org.vaadin.crudui.crud.CrudListener

@Service
class FloatManagerService(
    private val floatManagerRepository: FloatManagerRepository
):CrudListener<FloatManagerEntity> {
    override fun findAll(): MutableCollection<FloatManagerEntity> {
        return floatManagerRepository.findAll()
    }

    override fun delete(p0: FloatManagerEntity?) {
        if (p0 != null) {
            floatManagerRepository.delete(p0)
        }
    }

    override fun update(p0: FloatManagerEntity?): FloatManagerEntity {
        return floatManagerRepository.save(p0)!!
    }

    override fun add(p0: FloatManagerEntity?): FloatManagerEntity {
        return floatManagerRepository.save(p0)!!
    }

    fun count():Long{
        return floatManagerRepository.count()
    }
}