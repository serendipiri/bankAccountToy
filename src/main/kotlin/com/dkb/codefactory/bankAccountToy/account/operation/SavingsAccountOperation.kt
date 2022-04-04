package com.dkb.codefactory.bankAccountToy.account.operation

import com.dkb.codefactory.bankAccountToy.account.Account
import com.dkb.codefactory.bankAccountToy.account.AccountRepository
import com.dkb.codefactory.bankAccountToy.account.enums.AccountType
import com.dkb.codefactory.bankAccountToy.account.enums.OperationType
import com.dkb.codefactory.bankAccountToy.transaction.TransactionRepository
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class SavingsAccountOperation(repository: AccountRepository,
                              transactionRepository: TransactionRepository
) : AccountOperation(repository, transactionRepository) {

    override fun deposit(to: Long, amount: BigDecimal) : Account {
        throw RuntimeException("Depositing money to Savings Account is not allowed.")
    }

    override fun withdraw(from: Long, amount: BigDecimal) : Account {
        throw RuntimeException("Withdrawing money from Savings Account is not allowed.")
    }

    override fun transfer(from: Long, to: Long, amount: BigDecimal) : Account {

        var fromAccount = getAccountByIban(from)
        var toAccount = getAccountByIban(to)
        if (fromAccount.balance < amount) {
            throw RuntimeException("Remaining balance is not enough.")
        }

       if (fromAccount.type == AccountType.SAVINGS) {
            if (!fromAccount.checkingIbanForSavings?.equals(to)!!) {
                throw RuntimeException("You can transfer money from a savings account only to its checking account.")
            }
        }

        transferMoneyWithTransaction(fromAccount, toAccount, amount)
        return fromAccount
    }

}
