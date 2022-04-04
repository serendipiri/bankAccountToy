package com.dkb.codefactory.bankAccountToy.account

import com.dkb.codefactory.bankAccountToy.enums.AccountType
import org.springframework.data.jpa.repository.JpaRepository
import org.springframework.data.jpa.repository.Modifying
import org.springframework.data.jpa.repository.Query
import org.springframework.stereotype.Repository
import java.math.BigDecimal
import java.util.*

@Repository
interface AccountRepository: JpaRepository<Account, Long> {

    @Query("select a.balance from Account a where a.iban = :iban and a.blocked = false")
    fun getBalance(iban: Long): Optional<BigDecimal>

//    fun findByIban(checkingIbanForSavings: Long?): Account

//    @Query("select 1 from Account a where a.iban = :checkingIbanForSavings and a.type = :type and a.blocked = false")
//    fun isCheckingAccountExists(checkingIbanForSavings: Long?, type: AccountType): Boolean

    fun existsByIbanAndTypeAndBlockedFalse(checkingIbanForSavings: Long?, type: AccountType): Boolean

    fun findByTypeIn(types: Set<AccountType>): List<Account>

    @Query("select a.type from Account a where a.iban = :iban")
    fun getType(iban: Long): AccountType

}