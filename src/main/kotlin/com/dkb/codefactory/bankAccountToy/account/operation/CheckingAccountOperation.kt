package com.dkb.codefactory.bankAccountToy.account.operation

import com.dkb.codefactory.bankAccountToy.account.Account
import com.dkb.codefactory.bankAccountToy.account.AccountRepository
import com.dkb.codefactory.bankAccountToy.enums.OperationType
import com.dkb.codefactory.bankAccountToy.transaction.TransactionRepository
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class CheckingAccountOperation(repository: AccountRepository,
                               transactionRepository: TransactionRepository
) : AccountOperation(repository, transactionRepository) {

    override fun deposit(to: Long, amount: BigDecimal): Account {
        var account = getAccountByIban(to)
        val balanceBeforeOp = account.balance
        account.balance = account.balance?.plus(amount)
        createTransaction(null, account, amount, balanceBeforeOp, OperationType.DEPOSIT)
        return account
    }

    override fun withdraw(from: Long, amount: BigDecimal): Account {
        var account = getAccountByIban(from)
        val balanceBeforeOp = account.balance
        val res = account.balance?.minus(amount)

        if (res == null || res < BigDecimal(0)) {
            throw RuntimeException("Remaining balance is not enough.")
        }
        account.balance = res
        createTransaction(account, null, -amount, balanceBeforeOp, OperationType.WITDRAW)
        return account
    }

    override fun transfer(from: Long, to: Long, amount: BigDecimal): Account {
        var fromAccount = getAccountByIban(from)
        var toAccount = getAccountByIban(to)
        if (fromAccount.balance!! < amount) {
            throw RuntimeException("Remaining balance is not enough.")
        }

        transferMoneyWithTransaction(fromAccount, toAccount, amount)
        return fromAccount
    }

}