package com.dkb.codefactory.bankAccountToy.account.operation

import com.dkb.codefactory.bankAccountToy.account.Account
import com.dkb.codefactory.bankAccountToy.account.AccountRepository
import com.dkb.codefactory.bankAccountToy.enums.OperationType
import com.dkb.codefactory.bankAccountToy.transaction.Transaction
import com.dkb.codefactory.bankAccountToy.transaction.TransactionRepository
import java.math.BigDecimal
import java.time.LocalDate
import javax.transaction.Transactional

@Transactional
abstract class AccountOperation  (
    private val accountRepository: AccountRepository,
    private val transactionRepository: TransactionRepository
) {

    fun getAccountByIban (iban: Long): Account {
        var account = accountRepository.findById(iban)
        if(!account.isPresent)
            throw RuntimeException("Account with iban $iban is not available.")
        return account.get()
    }

    fun createTransaction(from: Account?, to: Account?, amount: BigDecimal, balanceBeforeOperation: BigDecimal, operationType: OperationType) {
        val date = LocalDate.now()
        var transaction = Transaction(null, amount, balanceBeforeOperation, date, operationType, from, to)
        transactionRepository.save(transaction)
    }

    fun transferMoneyWithTransaction(fromAccount: Account, toAccount: Account, amount: BigDecimal)
    {
        var balanceBeforeOp = fromAccount.balance
        fromAccount.balance = balanceBeforeOp.minus(amount)
        createTransaction(fromAccount, toAccount, -amount, balanceBeforeOp, OperationType.TRANSFER)

        balanceBeforeOp = toAccount.balance
        toAccount.balance = balanceBeforeOp.plus(amount)
        createTransaction(fromAccount, toAccount, amount, balanceBeforeOp, OperationType.TRANSFER)
    }

    abstract fun deposit(to: Long, amount: BigDecimal) : Account

    abstract fun withdraw(from: Long, amount: BigDecimal) : Account

    abstract fun transfer(from: Long, to: Long, amount: BigDecimal) : Account

}
