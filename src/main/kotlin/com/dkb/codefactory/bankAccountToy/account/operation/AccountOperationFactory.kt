package com.dkb.codefactory.bankAccountToy.account.operation

import com.dkb.codefactory.bankAccountToy.enums.AccountType
import org.springframework.stereotype.Component

@Component
class AccountOperationFactory (
    private val checkingAccountOperation: CheckingAccountOperation,
    private val savingsAccountOperation: SavingsAccountOperation,
    private val loanAccountOperation: LoanAccountOperation

) {

    fun getOperator(accountType: AccountType) : AccountOperation =
        when (accountType) {
            AccountType.CHECKING -> checkingAccountOperation
            AccountType.SAVINGS -> savingsAccountOperation
            AccountType.LOAN -> loanAccountOperation
            //TODO else ->
        }

}