package com.dkb.codefactory.bankAccountToy.transaction

import org.springframework.stereotype.Service

@Service
class TransactionService (private val transactionRepository: TransactionRepository){

    fun getAllTransactionsByIban(iban: Long) : List<Transaction> =
        transactionRepository.findTransactionsByIban(iban)

}