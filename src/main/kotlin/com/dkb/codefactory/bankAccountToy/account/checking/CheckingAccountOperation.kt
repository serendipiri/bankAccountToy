package com.dkb.codefactory.bankAccountToy.account.checking

import com.dkb.codefactory.bankAccountToy.account.Account
import com.dkb.codefactory.bankAccountToy.account.AccountRepository
import com.dkb.codefactory.bankAccountToy.account.operation.AccountOperation
import com.dkb.codefactory.bankAccountToy.transaction.TransactionRepository
import java.math.BigDecimal

class CheckingAccountOperation(repository: AccountRepository,
                               transactionRepository: TransactionRepository
) : AccountOperation(repository, transactionRepository) {

    override fun deposit(to: Long, amount: BigDecimal): Account {
        TODO("Not yet implemented")
    }

    override fun withdraw(from: Long, amount: BigDecimal): Account {
        TODO("Not yet implemented")
    }

    override fun transfer(from: Long, to: Long, amount: BigDecimal): Account {
        TODO("Not yet implemented")
    }


}