package com.dkb.codefactory.bankAccountToy.account.operation

import com.dkb.codefactory.bankAccountToy.account.Account
import com.dkb.codefactory.bankAccountToy.account.AccountRepository
import com.dkb.codefactory.bankAccountToy.enums.OperationType
import com.dkb.codefactory.bankAccountToy.transaction.TransactionRepository
import org.springframework.stereotype.Component
import java.math.BigDecimal

@Component
class LoanAccountOperation(repository: AccountRepository,
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
        throw RuntimeException("Withdrawing money from Loan Account is not allowed.")
    }

    override fun transfer(from: Long, to: Long, amount: BigDecimal): Account {
        throw RuntimeException("Transferring money from Loan Account is not allowed.")
    }

}
