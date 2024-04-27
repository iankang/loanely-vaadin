package com.loanely.application.configuration

import com.github.javafaker.Faker
import com.loanely.application.data.entities.*
import com.loanely.application.data.enums.Gender
import com.loanely.application.data.enums.Qualification
import com.loanely.application.services.*
import org.slf4j.Logger
import org.slf4j.LoggerFactory
import org.springframework.boot.CommandLineRunner
import org.springframework.stereotype.Component
import java.math.BigDecimal
import java.util.*

@Component
class DataLoader(
    private val faker: Faker = Faker(Locale.getDefault()),
    private val repaymentTypeService: RepaymentTypeService,
    private val loanChannelService: LoanChannelService,
    private val loanStatusService: LoanStatusService,
    private val subscriberService: SubscriberService,
    private val loanTypeService: LoanTypeService
) :CommandLineRunner{
    val logger: Logger = LoggerFactory.getLogger(this.javaClass)
    fun makeRepaymentTypeEntity(){
        if(repaymentTypeService.count() == 0L){
            val repaymentTypes = mutableListOf<RepaymentTypeEntity>(RepaymentTypeEntity("MANUAL"),RepaymentTypeEntity("SWEEP"))
            logger.info("adding repayment types")
            repaymentTypeService.addAll(repaymentTypes)
        }
    }

    fun makeLoanChannelEntityData(){
        if(loanChannelService.count() == 0L){
            val loanChannels = mutableListOf<LoanChannelEntity>(
                LoanChannelEntity("USSD"),
                LoanChannelEntity("MOBILE_APP"),
                LoanChannelEntity("WEBSITE"),
                LoanChannelEntity("SMS"),
                )
            logger.info("channelEntity data being added")
            loanChannelService.addAll(loanChannels)
        }
    }

    fun makeLoanStatusData(){
        if(loanStatusService.count() == 0L){
            val loanStati = mutableListOf<LoanStatus>(
                LoanStatus("OPEN"),
                LoanStatus("CLOSED"),
                LoanStatus("PENDING"),
                LoanStatus("FAILED"),
                )
            logger.info("adding loan stati")
            loanStatusService.addAll(loanStati)
        }
    }

    fun makeSubscribers(){
        if(subscriberService.count() == 0L){
            var subList = mutableSetOf<SubscriberEntity>()
            repeat(1500){
                val qualification = Qualification.entries.random()
                val sub = SubscriberEntity(
                    phoneNumber = faker.phoneNumber().subscriberNumber(),
                    fistName = faker.funnyName().name(),
                    lastName = faker.funnyName().name(),
                    gender = Gender.entries.toTypedArray().random(),
                    borrowableAmount = if(qualification.name.equals(Qualification.QUALIFIED)) BigDecimal.valueOf(15000L) else BigDecimal.ZERO,
                    limitAmount = if(qualification.name.equals(Qualification.QUALIFIED)) BigDecimal.valueOf(15000L) else BigDecimal.ZERO,
                    qualification = qualification
                )
                logger.info("subscriber info being added")
                subList.add(sub)
            }

            subscriberService.addAll(subList.toList())
        }
    }

    fun makeLoanTypesData(){
        if(loanTypeService.count() == 0L){
            val loanTypes = mutableListOf<LoanTypeEntity>(
                LoanTypeEntity("SALARY_ADVANCE"),
                LoanTypeEntity("UTILITY_LOAN"),
                LoanTypeEntity("MONTHLY_LOAN"),
                LoanTypeEntity("ASSET_LOAN"),
            )
            loanTypeService.addAll(loanTypes)
        }
    }
    override fun run(vararg args: String?) {
       makeRepaymentTypeEntity()
        makeLoanChannelEntityData()
        makeLoanStatusData()
        makeSubscribers()
        makeLoanTypesData()
    }
}