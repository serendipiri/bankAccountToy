package com.dkb.codefactory.bankAccountToy.account

import com.dkb.codefactory.bankAccountToy.account.dto.OperationRequestDTO
import com.dkb.codefactory.bankAccountToy.account.enums.AccountType
import com.dkb.codefactory.bankAccountToy.account.operation.AccountOperation
import com.dkb.codefactory.bankAccountToy.account.operation.AccountOperationFactory
import org.springframework.stereotype.Service
import java.math.BigDecimal
import javax.transaction.Transactional

@Service
class AccountService(
    private val repository: AccountRepository,
    private val accountOperationFactory: AccountOperationFactory
) {

    //TODO "Account with this iban is not valid."
    fun getBalance(iban: Long): BigDecimal = repository.getBalance(iban).orElse(BigDecimal(-1))

    fun saveAccount(account: Account): Account {
        val savedAccount = when (account.type) {
            AccountType.SAVINGS -> createSavingsAccount(account)
            else -> repository.save(account)
        }
        return savedAccount
    }

    private fun createSavingsAccount(account: Account): Account {
        val checkingAccountValid = repository.existsByIbanAndTypeAndBlockedFalse(account.checkingIbanForSavings, AccountType.CHECKING)
        if (checkingAccountValid)
            return repository.save(account)
        else
            throw RuntimeException("Checking account is not available for this savings account")
    }

    fun deposit(operation: OperationRequestDTO): Account {
        val operator = getOperatorOfAccount(operation.to)
        return operator.deposit(operation.to, operation.amount)
    }

    fun getAccounts(types: Set<AccountType>): List<Account> =
        repository.findByTypeIn(types)

    private fun getOperatorOfAccount(iban: Long): AccountOperation {
        val type = repository.getType(iban)
        return accountOperationFactory.getOperator(type)
    }

    @Transactional
    fun blockAccount(iban: Long): Account {
        var account = repository.findById(iban)
        when {
            !account.isPresent -> throw RuntimeException("Account with iban $iban is not valid.")
            else -> account.get().blocked = true
        }
        return account.get()
    }

    fun transfer(operation: OperationRequestDTO): Account {
        val operator = getOperatorOfAccount(operation.from)
        return operator.transfer(operation.from, operation.to, operation.amount)
    }

}