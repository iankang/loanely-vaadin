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
import java.util.concurrent.TimeUnit

@Component
class DataLoader(
    private val faker: Faker = Faker(Locale.getDefault()),
    private val repaymentTypeService: RepaymentTypeService,
    private val loanChannelService: LoanChannelService,
    private val loanStatusService: LoanStatusService,
    private val subscriberService: SubscriberService,
    private val loanTypeService: LoanTypeService,
    private val floatManagerService: FloatManagerService,
    private val loanService: LoanService,
    private val repaymentService: RepaymentService,
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

    fun addFloat(){
        if(floatManagerService.count() == 0L){
            val qualifiedAmount = subscriberService.countByQualification(Qualification.QUALIFIED).times(15000)
            floatManagerService.add(FloatManagerEntity(BigDecimal.valueOf(qualifiedAmount)))
        }
    }

    fun addLoans(){
        if(loanService.count() == 0L){
            val subs = subscriberService.findAll().filter { it.qualification == Qualification.QUALIFIED }
            val loanList = mutableListOf<Loans>()

            subs.forEach { sub ->

                val principal = Random().nextDouble(15000.00)
                val serviceFee = principal.times(0.1)
                val channel = loanChannelService.findAll().random()
                val loanTypi = loanTypeService.findAll().random()
                val loanStat = loanStatusService.findAll().random()
                val loan = Loans(
                    loanDate = faker.date().past(5,TimeUnit.DAYS),
                    dueDate = faker.date().future(5,TimeUnit.DAYS),
                    subscriber = sub,
                    principal = BigDecimal.valueOf(principal),
                    serviceFee = BigDecimal.valueOf(serviceFee),
                    loanChannelEntity = channel,
                    loanTypeEntity = loanTypi,
                    loanStatusEntity = loanStat
                )
                loanList.add(loan)
            }
            logger.info("adding loans")
            loanService.addAll(loanList)
        }
    }

    fun addRecoveries(){
        if(repaymentService.count() == 0L){
            val loans = loanService.findAll().filter { it.loanStatusEntity?.loanStatus == "CLOSED" }
            val repayments = mutableListOf<Repayment>()
            loans.forEach { loan ->
                val repaymentTypes = repaymentTypeService.findAll().random()
                val repayment = Repayment(
                    repayDate = faker.date().past(3,TimeUnit.DAYS),
                    subscriber = loan.subscriber,
                    loan = loan,
                    repaymentType = repaymentTypes,
                    amountPaid = loan.principal?.add(loan.serviceFee),
                    repaymentTransactionRef = UUID.randomUUID().toString()
                )
                repayments.add(repayment)
            }
            logger.info("adding repayments")
            repaymentService.addAll(repayments)
        }
    }
    override fun run(vararg args: String?) {
       makeRepaymentTypeEntity()
        makeLoanChannelEntityData()
        makeLoanStatusData()
        makeSubscribers()
        makeLoanTypesData()
        addFloat()
        addLoans()
        addRecoveries()
    }
}